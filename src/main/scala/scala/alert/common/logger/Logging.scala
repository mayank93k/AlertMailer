package scala.alert.common.logger

trait Logging {
  /**
   * Creates an SLF4J logger using the class name of the class that extends this trait.
   */
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
}
