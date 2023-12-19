package Output.ASCII

/**
 * Represents an empty implementation of ASCIIOutput, throwing exceptions when attempting to set or output an image.
 */
class ASCIIOutputEmpty extends ASCIIOutput {

  /**
   * Throws an exception since this is an empty implementation, and setting a new image is not allowed.
   *
   * @param newImage The new ASCII art image represented as a 2D array of characters.
   * @throws Exception Always throws an exception indicating that setting a new image is not allowed.
   */
  override def set(newImage: Array[Array[Char]]): Unit = {
    throw new Exception("Cannot use empty output methods. Set correct output.")
  }

  /**
   * Throws an exception since this is an empty implementation, and outputting an image is not allowed.
   *
   * @throws Exception Always throws an exception indicating that outputting an image is not allowed.
   */
  override def output(): Unit = {
    throw new Exception("Cannot use empty output methods. Set correct output.")
  }
}
