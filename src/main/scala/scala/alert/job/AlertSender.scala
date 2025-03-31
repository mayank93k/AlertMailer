package scala.alert.job

import scala.alert.utility.{AlertMessage, EmailAlertParser, UtilityFunction}

object AlertSender {
  def main(args: Array[String]): Unit = {
    AlertMessage.addMessage("Hello! this is the test mail")
    UtilityFunction.AlertProcessor("TEST")
  }

}
