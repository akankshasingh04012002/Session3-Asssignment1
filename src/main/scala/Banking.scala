import scala.collection.mutable
import scala.util.Random

case class Transactions(transactionId: Long, accountNumber: Long, transactionType: String, amount: Double)

class Banking {
  val account: mutable.Map[Long, Double] = mutable.Map[Long, Double]()

  def createAccount(openingBalance: Double): mutable.Map[Long, Double] = {
    val accountNumber = Random.nextLong().abs
    account += (accountNumber -> openingBalance)
    account
  }

  def listAccounts(): mutable.Map[Long, Double] = {
    account
  }

  def fetchAccountBalance(accountNumber: Long): Double = {
    val balance = account(accountNumber)
    balance
  }

  def updatedBalance(transactions: List[Transactions]): mutable.Map[Long, Double] = {
    transactions.foreach { transaction =>
      val currentBalance = account.getOrElse(transaction.accountNumber, 0.0)
      val updateBalance = transaction.transactionType match {
        case "credit" => currentBalance + transaction.amount
        case "debit" => currentBalance - transaction.amount
        case _ => currentBalance
      }
      account += (transaction.accountNumber -> updateBalance)
    }
    account
  }

  def deleteAccount(accountNumber: Long): Boolean = {
    if (account.contains(accountNumber)) {
      account -= accountNumber
      true
    } else {
      false
    }
  }
}




