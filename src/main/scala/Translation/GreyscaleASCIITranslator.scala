package Translation
import Image.GreyscaleImage
import Translation.Type.{ASCIITranslation, LinearASCIITranslation, NonLinearASCIITranslation}

/**
 * Translates a GreyscaleImage into ASCII art using different translation types.
 *
 * @param image The GreyscaleImage to be translated.
 */
class GreyscaleASCIITranslator(image: GreyscaleImage) extends Translator[Array[Array[Char]]] {

  // Linear translation set as default
  private var translationType: ASCIITranslation = new LinearASCIITranslation()
  private var input: String = ""

  /**
   * Sets the character table for translation based on a known table name.
   *
   * @param table_name The name of the known character table.
   *                   Valid options: "standard", "ten_levels".
   * @throws Exception If the specified character table is unknown.
   */
  def choiceKnownTable(table_name: String): Unit = {
    table_name match {
      case "standard" =>
        input = " .'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"
      case "ten_levels" =>
        input = " .:-=+*#%@"
      case _ =>
        throw new Exception("Unknown character table.")
    }
  }

  /**
   * Sets the character table for translation based on a custom input string.
   *
   * @param funcInput The custom character table represented as a string.
   * @throws Exception If the input string is empty.
   */
  def choiceTable(funcInput: String): Unit = {
    if (funcInput.isEmpty) {
      throw new Exception("Empty character table is not valid table.")
    }

    input = funcInput
  }

  /**
   * Translates the GreyscaleImage into ASCII art using the selected translation method.
   *
   * @return The translated ASCII art as a 2D array of characters.
   */
  def translate(): Array[Array[Char]] = {
    val asciiImage = translationType.toASCII(image.get, input)
    asciiImage
  }

  /**
   * Sets the translation method to linear ASCII translation.
   */
  def useLinearTranslation(): Unit = translationType = new LinearASCIITranslation()

  /**
   * Sets the translation method to non-linear ASCII translation.
   */
  def useNonLinearTranslation(): Unit = translationType = new NonLinearASCIITranslation()
}
