package scala.alert.utility

object UtilityFunction {
  def AlertProcessor(sourceName: String): Unit = {
    val combinedMessage = AlertMessage.getCombinedMessage
    EmailAlertParser(combinedMessage, sourceName)
  }
}
