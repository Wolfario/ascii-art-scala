package Output

/**
 * A trait representing an common output.
 */
trait Output[T] {

  /**
   * Outputs.
   */
  def output(): Unit

  /**
   * Sets.
   */
  def set(newImage: T): Unit
}
