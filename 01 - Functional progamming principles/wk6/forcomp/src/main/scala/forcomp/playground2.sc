
import forcomp.Anagrams._

val x = List(List(4,0),List(5,0),List(6,0))

x flatMap (l => (1 to 2) map (y => y :: l))

for (l <- x; n <- 1 to 2) yield n :: l

val ocurrence =  combinations(sentenceOccurrences(List("la", "el")))

sentenceOccurrences(List("la", "el"))





