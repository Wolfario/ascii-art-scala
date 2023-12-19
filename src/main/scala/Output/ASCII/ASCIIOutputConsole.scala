package Output.ASCII

/**
 * Represents an ASCII output console for displaying ASCII art images.
 */
class ASCIIOutputConsole() extends ASCIIOutput {

  private var image: Array[Array[Char]] = Array.empty

  /**
   * Outputs the ASCII art image to the console.
   *
   * @throws Exception If the output is not set (image is empty).
   */
  override def output(): Unit = {
    if (image.isEmpty)
      throw new Exception("Output isn't set.")

    for (row <- image) {
      for (char <- row) {
        print(char)
      }
      println()
    }
  }

  /**
   * Sets the ASCII art image to be displayed on the console.
   *
   * @param newImage The new ASCII art image represented as a 2D array of characters.
   */
  override def set(newImage: Array[Array[Char]]): Unit = image = newImage
}
