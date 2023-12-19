package Translation.Type

/**
 * Trait of linear ASCII translation of a greyscale image.
 */
trait ASCIITranslation {

  /**
   * Translates a greyscale image into ASCII art.
   *
   * @param greyscaleImage The 2D array representing the greyscale image.
   * @param input          The input that defines the translation method.
   * @return The translated ASCII art as a 2D array of characters.
   */
  def toASCII(greyscaleImage: Array[Array[Int]], input: String): Array[Array[Char]]

  /**
   * Distributes intensity values of a greyscale image to corresponding ASCII characters.
   *
   * @param greyscaleImage The 2D array representing the greyscale image.
   * @param characters     The list of ASCII characters used for mapping intensity values.
   * @return The translated ASCII art as a 2D array of characters.
   */
  def distributeToASCII(greyscaleImage: Array[Array[Int]], characters: List[Char]): Array[Array[Char]] = {
    val range = 255f / characters.length.toFloat
    val asciiReturn: Array[Array[Char]] = greyscaleImage.map {
      row =>
        row.map {
          pixelValue =>
            var char_index = (pixelValue / range).floor.toInt
            if (char_index >= characters.length) {
              char_index = characters.length - 1
            }
            characters(char_index)
        }
    }
    asciiReturn
  }
}
