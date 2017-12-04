package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(a, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min(a,b) = inserting a and b in empty h + get findMin(h)") = forAll {(a1:A, a2:A) =>
    findMin(insert(a1, insert(a2, empty))) == ord.min(a1, a2)
  }

  property("inserting some a in empty h and deleting a = empty h") = forAll {(a:A) =>
    isEmpty(deleteMin(insert(a,empty)))
  }

  property("Given any heap, sorted sequence of elements = continually finding and deleting min") = forAll {(h:H) =>
    def loop(hs:H, l:List[A]): List[A] = if (isEmpty(hs)) l else loop(deleteMin(hs), findMin(hs) :: l)
    val test = loop(h,List())
    test == test.sortWith(_ > _)
  }

  property("given some h1 and h2, the minimum of melting h1 and h2 = min of h1 or h2") = forAll{(h1: H,h2: H) =>
    findMin(meld(h1, h2)) == ord.min(findMin(h1), findMin(h2))
  }

  property("Two heaps should be equal if recursivly removing min elements result in same elements until empty") = forAll { (h1: H, h2: H) =>
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val m1 = findMin(h1)
        val m2 = findMin(h2)
        m1 == m2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    heapEqual(meld(h1, h2), meld(deleteMin(h1), insert(findMin(h1), h2)))
  }


}
