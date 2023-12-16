package Translation.Type

class NonLinearASCIITranslation() extends ASCIITranslation {
  override def toASCII(greyscaleImage: Array[Array[Int]], input: String): Array[Array[Char]] = {
    val processedSet = processInput(input)
    val atomicCharacters = processedSet._1
    val priority = processedSet._2

    val pairs = atomicCharacters.zip(priority)
    val characters = pairs.flatMap { case (char, count) => List.fill(count)(char) }

    distributeToASCII(greyscaleImage, characters)
  }

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
