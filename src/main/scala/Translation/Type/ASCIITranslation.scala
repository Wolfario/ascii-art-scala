package Translation.Type

trait ASCIITranslation {

  def toASCII(greyscaleImage: Array[Array[Int]], input: String): Array[Array[Char]]

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
