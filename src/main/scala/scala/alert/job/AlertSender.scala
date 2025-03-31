package scala.alert.job

import scala.alert.utility.{AlertMessage, UtilityFunction}

object AlertSender {

  /**
   * Entry point of the application.
   * This method sends a test alert message and processes the alert.
   *
   * @param args Command-line arguments (not used in this example).
   */
  def main(args: Array[String]): Unit = {
    AlertMessage.addMessage("Hello! this is the test mail")
    UtilityFunction.AlertProcessor("TEST")
  }
}
