package Output.ASCII
import Output.Output

/**
 * A trait representing an ASCII output for displaying and saving ASCII art images.
 */
trait ASCIIOutput extends Output[Array[Array[Char]]] {

  /**
   * Outputs the ASCII art image.
   */
  def output(): Unit

  /**
   * Sets the ASCII art image to be displayed.
   *
   * @param newImage The new ASCII art image represented as a 2D array of characters.
   */
  def set(newImage: Array[Array[Char]]): Unit
}
