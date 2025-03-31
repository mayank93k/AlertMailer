package scala.alert.utility

import com.typesafe.config.ConfigFactory

import javax.mail._
import javax.mail.internet.{InternetAddress, MimeMessage}
import scala.alert.common.logger.Logging

object EmailAlertParser extends Logging {
  /**
   * Sends an alert email with the given message and source name.
   *
   * @param message    The alert message content.
   * @param sourceName The source identifier for the alert.
   *
   *                   This method loads email configuration from `jobConfig.conf`,
   *                   sets up the SMTP properties, creates an email session with authentication,
   *                   constructs an email, and sends it using `Transport.send`.
   *                   After sending the email, it clears the alert messages.
   */
  def apply(message: String, sourceName: String): Unit = {
    logger.info("Sending alert email...")

    /**
     * Load email configuration from jobConfig.conf
     */
    val config = ConfigFactory.load("jobConfig.conf")
    val targetMail = config.getString("targetMail")
    val hostAddress = config.getString("hostAddress")
    val port = config.getString("port")

    // Set up email properties for the SMTP server
    val properties = System.getProperties
    properties.setProperty("mail.smtp.host", hostAddress)
    properties.put("mail.smtp.auth", "true")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.host", hostAddress)
    properties.put("mail.smtp.port", port)

    /**
     * Create an email session with authentication.
     * Uses `targetMail` for authentication, but password needs to be securely provided.
     */
    val session = Session.getInstance(properties, new Authenticator() {
      override protected def getPasswordAuthentication =
        new PasswordAuthentication(targetMail, "")
    })
    try {
      // Construct the email message
      val mimeMessage = new MimeMessage(session)
      mimeMessage.setFrom(new InternetAddress("noreply@gmail.com", "Email Alert"))
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(targetMail))
      mimeMessage.setSubject("Test Alert In Scala")
      mimeMessage.setText(message)

      // Send the email
      Transport.send(mimeMessage)

      logger.info("Alert email send successfully")
      // Clear stored alert messages after successful email dispatch
      AlertMessage.clearMessage()
      logger.info("Alert message list cleared.")
    } catch {
      case e: Exception => logger.error("Failed to send alert mail.", e)
    }
  }

}
