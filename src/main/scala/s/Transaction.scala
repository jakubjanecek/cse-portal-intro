package s

class Transaction {

  def begin(): Unit = println("tx begin")

  def commit(): Unit = println("commit")

  def rollback(): Unit = println("rollback")

}

object Transaction {

  def tx[T](block: => T): Option[T] = {
    val transaction = new Transaction

    try {
      transaction.begin()

      val result = block

      transaction.commit()

      Some(result)
    }
    catch {
      case _ => {
        transaction.rollback()
        None
      }
    }
  }

}
