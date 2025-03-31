# AlertMailer
    AlertMailer is a lightweight email notification system built in Scala using the JavaMail API. 
    It enables sending automated email alerts for monitoring systems, event notifications, and error reporting. 
    Supports SMTP authentication, TLS encryption, and customizable email content. 
    Ideal for integration with Spark, Kafka, and other real-time data pipelines.

# Email Alert System in Scala

## Overview
    This Scala-based Email Alert System is designed to send alert emails based on specific conditions. 
    It loads email configurations from jobConfig.conf, constructs an email using JavaMail, and sends it through an SMTP server. 
    After successfully sending an email, it clears the stored alert messages.

## Features
    Stores alert messages using AlertMessage.
    Processes and sends alert emails via EmailAlertParser.
    Uses JavaMail API for email communication.  
    Loads SMTP configurations dynamically from jobConfig.conf.
    Supports logging for debugging and monitoring.

## Project Structure
    project_root/
    │── src/
    │   ├── main/
    │   │   ├── scala/
    │   │   │   ├── AlertMessage.scala
    │   │   │   ├── AlertProcessor.scala
    │   │   │   ├── EmailAlertParser.scala
    │── resources/
    │   ├── jobConfig.conf
    │── README.md

## Installation & Setup
#### Prerequisites
    Scala 2.12+
    sbt (Scala Build Tool)  
    JavaMail API (javax.mail)
    Configuration file jobConfig.conf
#### Step 1: Clone the Repository
    git clone https://github.com/your-repo/email-alert-system.git
    cd email-alert-system
#### Step 2: Configure SMTP Settings
    Modify resources/jobConfig.conf with your SMTP server details:
    targetMail = "recipient@example.com"
    hostAddress = "smtp.example.com"
    port = "587"
#### Step 3: Build & Run the Application
    sbt compile
    sbt run

## Usage
1. Add an Alert Message

    AlertMessage.addMessage("System is down!")

2. Process Alerts and Send Email

    AlertProcessor("System Monitor")
   
### Logging
This project uses logger for logging important events. Errors are logged if an email fails to send.

### Error Handling
If email configuration is missing, an exception is logged.
If the SMTP server is unavailable, an error message is displayed.


