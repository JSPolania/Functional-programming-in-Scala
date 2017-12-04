/**
  * Created by Tato on 31-08-17.
  */
package patmat

import patmat.Huffman._
import scala.util.Random

object pruebas {

  val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
  val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4), Leaf('z',7), Leaf('a', 9))
  val t3 = until(singleton, combine)(leaflist)


  def main(args: Array[String]): Unit = {

    println("Hello, Scala developer!")
//    val decodedsecret = decode(frenchCode, secret)
//    println(decodedsecret.mkString)
//    println(secret)
//    println(encode(frenchCode)(decodedsecret))
//    val randomtext = for (i <- 1 to 2000) yield (Random.nextInt(25) + 97).asInstanceOf[Char]
//    val randomEncoded = encode(frenchCode)(randomtext.toList)
//    println(decode(frenchCode, randomEncoded) == randomtext.toList)
//
//    println(randomtext)
//
//    println(times(List.fill(10000)(List('a','b','c')).flatten))
//
//    println(encodeChar(frenchCode, 'l'))

    println(codeBits(convert(frenchCode))('l'))

    val frenchCodeTable = convert(frenchCode)
    println(frenchCodeTable)
    val decodedSecret = decode(frenchCode, secret)
    println(decodedSecret.flatMap(codeBits(frenchCodeTable)(_)) == secret)


  }
}

///List(h, u, f, f, m, a, n, e, s, t, c, o, o, l)