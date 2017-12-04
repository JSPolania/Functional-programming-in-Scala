import sun.misc.Signal

class BankAccount {
  val balance = Var(0)
  def currentBalance: Int = balance
  def deposit(amount: Int): Unit =
    if (amount > 0) {
      balance() = balance() + amount
    }
  def withdraw(amount: Int) =
    if (0 < amount && amount <= balance()) {
      balance()  = balance() - amount
    } else throw new Error("insifficient founds")
}

def consolidated(accts: List[BankAccount]): Signal

