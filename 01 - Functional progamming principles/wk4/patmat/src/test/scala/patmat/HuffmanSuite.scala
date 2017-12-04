package patmat

import org.scalatest.FunSuite

import scala.util.{Random => r}

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("tests for times with empty, singleton and big (30000) char lists") {
    assert(times(List()) === List())
    assert(times(List('a')) === List(('a', 1)))
    val bigList = List.fill(10000)(List('a','b','c')).flatten
    assert(times(bigList) === List(('a',10000), ('b',10000), ('c',10000)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("checking for singleton treelist") {
    assert(!singleton(List(Leaf('a',2), Leaf('b',3))))
    assert(singleton(List(Leaf('a',2))))
    assert(!singleton(List()))
  }


  test("combine of some leaf list") {
    val leaflist1 = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist1) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
    val singletonleaf = List(Leaf('a',1))
    assert(combine(singletonleaf) === List(Leaf('a',1)))
    assert(combine(List()) === Nil)
  }

  test("decoding the big secret") {
    assert(decode(frenchCode, secret) == "huffmanestcool".toList)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a big text") {
    val randomText = for (i <- 1 to 100000) yield (r.nextInt(25) + 97).asInstanceOf[Char]
    val randomEncoded = encode(frenchCode)(randomText.toList)
    assert(decode(frenchCode, randomEncoded) == randomText.toList)
  }

  test("check secret letters with the french code tree and CodeTable") {
    val frenchCodeTable = convert(frenchCode)
    val decodedSecret = decode(frenchCode, secret)
    assert(decodedSecret.flatMap(codeBits(frenchCodeTable)(_)) == secret)
  }

  test("convert frencode Treecode to ListCode") {
    val frenchCodeTable = convert(frenchCode)
    val decodedSecret = decode(frenchCode, secret)
    assert(decodedSecret.flatMap(codeBits(frenchCodeTable)(_)) == secret)
  }

  test("test for quickencode") {
    val randomText = for (i <- 1 to 100000) yield (r.nextInt(25) + 97).asInstanceOf[Char]
    val randomEncoded = quickEncode(frenchCode)(randomText.toList)
    assert(decode(frenchCode, randomEncoded) == randomText.toList)
  }

}
