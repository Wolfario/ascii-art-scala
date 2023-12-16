package Translation.Type

class LinearASCIITranslation() extends ASCIITranslation {

  override def toASCII(greyscaleImage: Array[Array[Int]], input: String): Array[Array[Char]] = {
    if (!areAllCharactersUnique(input))
      throw new Exception("Symbols in character table are not unique or you have chosen wrong translation mode.")

    val characters = input.toList
    distributeToASCII(greyscaleImage, characters)
  }

  private def areAllCharactersUnique(inputString: String): Boolean = {
    val uniqueChars = inputString.toSet
    uniqueChars.size == inputString.length
  }
}
