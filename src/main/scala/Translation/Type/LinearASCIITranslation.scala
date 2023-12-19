package Translation.Type

/**
 * Performs linear ASCII translation of a greyscale image using a specified character table.
 */
class LinearASCIITranslation() extends ASCIITranslation {

  /**
   * Translates a greyscale image into ASCII art using a linear mapping of intensity values to characters.
   *
   * @param greyscaleImage The 2D array representing the greyscale image.
   * @param input          The character table used for translation.
   * @return The translated ASCII art as a 2D array of characters.
   * @throws Exception If the characters in the input table are not unique or if an incorrect translation mode is chosen.
   */
  override def toASCII(greyscaleImage: Array[Array[Int]], input: String): Array[Array[Char]] = {
    if (!areAllCharactersUnique(input))
      throw new Exception("Symbols in character table are not unique or you have chosen wrong translation mode.")

    val characters = input.toList
    distributeToASCII(greyscaleImage, characters)
  }

  /**
   * Checks if all characters in the input string are unique.
   *
   * @param inputString The input string to check for unique characters.
   * @return true if all characters are unique, false otherwise.
   */
  private def areAllCharactersUnique(inputString: String): Boolean = {
    val uniqueChars = inputString.toSet
    uniqueChars.size == inputString.length
  }
}
