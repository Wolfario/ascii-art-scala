package Translation.Type

/**
 * Performs non-linear ASCII translation of a greyscale image using a custom character table.
 */
class NonLinearASCIITranslation() extends ASCIITranslation {

  /**
   * Translates a greyscale image into ASCII art using a non-linear mapping of intensity values to characters.
   *
   * @param greyscaleImage The 2D array representing the greyscale image.
   * @param input          The custom character table used for translation.
   * @return The translated ASCII art as a 2D array of characters.
   * @throws Exception If an invalid custom non-linear table input is provided.
   *                   Also throws an exception if the sum of non-linear table indexes does not equal 255.
   */
  override def toASCII(greyscaleImage: Array[Array[Int]], input: String): Array[Array[Char]] = {
    val processedSet = processInput(input)
    val atomicCharacters = processedSet._1
    val priority = processedSet._2

    val pairs = atomicCharacters.zip(priority)
    val characters = pairs.flatMap { case (char, count) => List.fill(count)(char) }

    distributeToASCII(greyscaleImage, characters)
  }

  /**
   * Processes the input string to extract character-value pairs for building the non-linear table.
   *
   * @param input The input string containing character-value pairs separated by ";".
   * @return A tuple containing lists of characters and corresponding values.
   * @throws Exception If the input contains invalid data or the sum of values is not equal to 255.
   */
  private def processInput(input: String): (List[Char], List[Int]) = {
    var systemExceptionFlag = true
    try {
      val keyValuePairs = input.split(";")

      var totalValue = 0

      val result = keyValuePairs.map { pair =>
        val Array(key, value) = pair.split(":")
        val intValue = value.toInt

        if (intValue < 0 || intValue > 255) {
          systemExceptionFlag = false
          throw new Exception("Sum of non-linear table indexes does not equal 255.")
        }

        totalValue += intValue
        (key.charAt(0), intValue)
      }

      if (totalValue != 255) {
        systemExceptionFlag = false
        throw new Exception("Sum of non-linear table indexes does not equal 255.")
      }

      val (characters, values) = result.unzip
      (characters.toList, values.toList)
    } catch {
      case e: Exception =>
        if (systemExceptionFlag) {
          throw new Exception("Invalid custom non-linear table input.")
        }
        else {
          throw new Exception(e.getMessage)
        }
    }
  }
}
