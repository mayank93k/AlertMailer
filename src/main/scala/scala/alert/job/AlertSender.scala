package scala.alert.job

import scala.alert.utility.UtilityFunction.addBeautifiedHeaderMessage
import scala.alert.utility.{AlertMessage, UtilityFunction}

object AlertSender {

  /**
   * Entry point of the application.
   * This method sends a test alert message and processes the alert.
   *
   * @param args Command-line arguments (not used in this example).
   */
  def main(args: Array[String]): Unit = {
    addBeautifiedHeaderMessage("Error In Your System")
    AlertMessage.addMessage("Hello! Message me hi on what's app!!")
    UtilityFunction.AlertProcessor("TEST")
  }
}
