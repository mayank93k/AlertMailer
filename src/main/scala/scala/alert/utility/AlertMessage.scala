package scala.alert.utility


case class AlertMessage(message: List[String])

object AlertMessage {
  private val messages = scala.collection.mutable.ListBuffer[String]()

  def addMessage(message: String): Unit = {
    messages += message
  }

  def getCombinedMessage: String = {
    messages.mkString
  }

  def clearMessage(): Unit = {
    messages.clear()
  }


}
