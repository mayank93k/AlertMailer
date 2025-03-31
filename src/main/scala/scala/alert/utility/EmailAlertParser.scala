package scala.alert.utility

import com.typesafe.config.ConfigFactory

import javax.mail.internet.{InternetAddress, MimeMessage}
import javax.mail.{Authenticator, Message, PasswordAuthentication, Session, Transport}
import scala.alert.common.logger.Logging

object EmailAlertParser extends Logging {
  def apply(message: String, sourceName: String): Unit = {
    logger.info("Sending alert email...")

    /**
     * Load configuration from jobConfig.conf
     */
    val config = ConfigFactory.load("jobConfig.conf")
    val targetMail = config.getString("targetMail")
    val hostAddress = config.getString("hostAddress")
    val port = config.getString("port")

    val properties = System.getProperties
    properties.setProperty("mail.smtp.host", hostAddress)
    properties.put("mail.smtp.auth", "true")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.host", hostAddress)
    properties.put("mail.smtp.port", port)

   // val session = Session.getDefaultInstance(properties)
    val session = Session.getInstance(properties, new Authenticator() {
      override protected def getPasswordAuthentication =
        new PasswordAuthentication(targetMail, "uzqt rwjv thnf uenl")
    })
    try {
      val mimeMessage = new MimeMessage(session)
      mimeMessage.setFrom(new InternetAddress("noreply@gmail.com", "Email Alert"))
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(targetMail))
      mimeMessage.setSubject("Test Alert In Scala")
      mimeMessage.setText(message)

      Transport.send(mimeMessage)

      logger.info("Alert email send successfully")
      AlertMessage.clearMessage()
      logger.info("Alert message list cleared.")
    } catch {
      case e: Exception => logger.error("Failed to send alert mail.", e)
    }
  }

}
