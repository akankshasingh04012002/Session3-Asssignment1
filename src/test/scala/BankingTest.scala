import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BankingTest extends AnyFlatSpec with Matchers {
  val bankingTestObj = new Banking
  "it" should "match with account" in {
    val account = bankingTestObj.createAccount(1000)
    println(account)

  }

  "Banking" should "create a new account" in {
    val accountNumber = bankingTestObj.createAccount(1000.0)
    bankingTestObj.listAccounts() shouldBe accountNumber
  }

  it should "list all accounts with balance" in {
    val accountNumber1 = bankingTestObj.createAccount(1000.0)
    bankingTestObj.listAccounts() should be(accountNumber1)
  }

  it should "fetch account balance using account number" in {

    val accountNumber = bankingTestObj.createAccount(1000.0).keys.toList(0)
    bankingTestObj.fetchAccountBalance(accountNumber) shouldBe 1000.0
  }

  it should "update account balance based on a list of transactions" in {
    val account = bankingTestObj.createAccount(5000.0)
    val transactions = List(
      Transactions(1l, account.keys.toList(0), "credit", 1000.0),
    )
    bankingTestObj.updatedBalance(transactions).values.toList(0) shouldBe 2000.0

  }

  it should "delete account using account number" in {
    val account = bankingTestObj.createAccount(5000.0)
    val accountNumber = bankingTestObj.createAccount(1000.0)
    bankingTestObj.deleteAccount(account.keys.toList(0)) should be(true)

  }
}