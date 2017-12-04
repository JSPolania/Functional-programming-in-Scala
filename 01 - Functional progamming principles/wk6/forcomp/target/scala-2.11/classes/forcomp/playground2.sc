
import forcomp.Anagrams._


dictionaryByOccurrences get List(('a', 1), ('e', 1), ('t', 1))

wordAnagrams("married")

def powerSet(xs: List[Int]): List[List[Int]] = {
  def go(xsRemaining: List[Int], sets: List[List[Int]]): List[List[Int]] = xsRemaining match {
    case Nil => sets
    case y :: ys => go(ys, sets ::: sets.map(_ :+ y))
  }
  go(xs, List(Nil))
}

val x = List(List(4,0),List(5,0),List(6,0))

x flatMap (l => (1 to 2) map (y => y :: l))

for (l <- x; n <- 1 to 2) yield n :: l

//def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
//  val ocurrence = sentenceOccurrences(sentence)
//}


val ocurrence = sentenceOccurrences(List("Linux", "rulez"))


