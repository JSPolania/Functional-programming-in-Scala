
trait Generator[+T] {
  self =>
  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate: S = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]):Generator[S] = new Generator[S] {
    def generate: S = f(self.generate).generate
  }

}

val integers = new Generator[Int] {
  val rand = new java.util.Random
  def generate = rand.nextInt()
}

val booleans = for {x <- integers} yield x < 0
val booleans1 = integers map (x => x < 0) //implementation

booleans1.generate

val pairs1 = for {x <- integers; y <- integers} yield (x,y)
val pairs11 = integers flatMap(x => for {y <- integers} yield (x,y))   //expansion1
val pairs12 = integers flatMap(x => integers map (y => (x,y)))         //expansion2


def pairs[T,U](t:Generator[T], u:Generator[U]): Generator[(T,U)] =
  for {x <- t; y <- u} yield (x,y)

def single[T](x: T):Generator[T] = new Generator[T] {
  def generate = x
}

def choose(lo:Int, hi:Int) = for {x <- integers} yield lo + x % (hi - lo)

def oneOf[T](xs: T*): Generator[T] =
for {idx <- choose(0, xs.length)} yield xs(idx)

def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if (isEmpty) emptyLists else nonEmptyList
} yield list

def emptyLists = single(Nil)

def nonEmptyList = for {
  head <- integers
  tail <- lists
} yield head :: tail

lists.generate

trait Tree

case class Inner(left: Tree, right:Tree) extends Tree

case class Leaf(x: Int) extends Tree

def trees:Generator[Tree] = for {
  isLeaf <- booleans
  tree <- if (isLeaf) leafTree else innerTree
} yield tree

def leafTree = for (x <- integers) yield Leaf(x)

def innerTree = for {left <- trees; right <- trees} yield Inner(left, right)


trees.generate

