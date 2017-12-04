
class Signal[T](expr: => T) {
  import Signal._
  private var myEpxr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)

  protected def update(expr: => T): Unit = {
    myEpxr = () => expr
    computeValue()
  }

  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myEpxr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }
  def apply() = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }
}

object Signal {
  private val caller = new scala.util.DynamicVariable[Signal[_]](NoSignal)
     // Variable[Signal[_]](NoSignal)
  def apply[T](expr: => T) = new Signal(expr)
}

class Var[T](expr: => T) extends Signal[T](expr) {
  override def update(expr: => T): Unit = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}

class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)
  def value: T = values.head
  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values
    try op finally values = values.tail
  }
}

object NoSignal extends Signal[Nothing](???) {
  override def computeValue() = ()
}


//
//class BankAccount {
//  val balance = Var(0)
//  def currentBalance: Int = balance
//  def deposit(amount: Int): Unit =
//    if (amount > 0) {
//      balance() = balance() + amount
//    }
//  def withdraw(amount: Int) =
//    if (0 < amount && amount <= balance()) {
//      balance()  = balance() - amount
//    } else throw new Error("insifficient founds")
//}
//
//def consolidated(accts: List[BankAccount]): Signal[Int] =
//  Signal(accts.map(_.balance()).sum)

