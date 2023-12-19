package Output.ASCII
import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

/**
 * Represents an ASCII output to a file for displaying and saving ASCII art images.
 *
 * @param path The file path where the ASCII art image will be saved.
 */
class ASCIIOutputFile(path: String) extends ASCIIOutput {

  /**
   * Initializes an ASCIIOutputFile, creating the file at the specified path if it does not exist.
   */
  if (!Files.exists(Paths.get(path))) {
    Files.createFile(Paths.get(path))
  }

  private var image: Array[Array[Char]] = Array.empty
  private val file = new File(path)

  /**
   * Outputs the ASCII art image to the specified file.
   *
   * @throws Exception If the output is not set (image is empty).
   */
  override def output(): Unit = {
    if (image.isEmpty) {
      throw new Exception("Output isn't set.")
    }

    val writer = new PrintWriter(file)
    for (row <- image) {
      for (char <- row) {
        writer.print(char)
      }
      writer.println()
    }
    writer.close()
  }

  /**
   * Sets the ASCII art image to be saved to the file.
   *
   * @param newImage The new ASCII art image represented as a 2D array of characters.
   */
  override def set(newImage: Array[Array[Char]]): Unit = image = newImage
}
