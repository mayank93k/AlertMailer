package scala.alert.utility

import scala.alert.common.logger.Logging

object UtilityFunction extends Logging {
  /**
   * Processes alert messages and sends them for parsing.
   *
   * @param sourceName The source identifier for the alert.
   *
   *                   This function retrieves the combined alert message from `AlertMessage`,
   *                   and passes it to `EmailAlertParser`. If no messages are present,
   *                   it logs a message and exits. It also includes basic error handling
   *                   to prevent failures if `EmailAlertParser` throws an exception.
   */
  def AlertProcessor(sourceName: String): Unit = {
    val combinedMessage = AlertMessage.getCombinedMessage

    if (combinedMessage.nonEmpty) {
      try {
        EmailAlertParser(combinedMessage, sourceName)
      } catch {
        case e: Exception =>
          logger.error(s"Error processing alert: ${e.getMessage}")
      }
    } else {
      logger.warn("No alerts to process.")
    }
  }
}
