package Translation
import Image.ImageRGB.RandomImage
import org.scalatest.FunSuite

class GreyscaleASCIITranslatorTest extends FunSuite {

  test("Selecting non-existing table.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

      asciiTranslator.choiceKnownTable("this will never be a name of table!")
    }
    assert(exception.getMessage == "Unknown character table.")
  }

  test("Try to choice empty custom table.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

      asciiTranslator.choiceTable("")
    }
    assert(exception.getMessage == "Empty character table is not valid table.")
  }

  test("Applying valid input of linear translation with known table on random image.") {
    val randomImage = new RandomImage(150, 150)
    val greyscaleTranslator = new GreyscaleTranslator(randomImage)
    val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

    asciiTranslator.useLinearTranslation()
    asciiTranslator.choiceKnownTable("ten_levels")
    asciiTranslator.translate()
  }

  test("Applying valid input of linear translation with custom table on random image.") {
    val randomImage = new RandomImage(150, 150)
    val greyscaleTranslator = new GreyscaleTranslator(randomImage)
    val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

    asciiTranslator.useLinearTranslation()
    asciiTranslator.choiceTable(" +N")
    asciiTranslator.translate()
  }

  test("Applying valid input of non-linear translation on random image.") {
    val randomImage = new RandomImage(150, 150)
    val greyscaleTranslator = new GreyscaleTranslator(randomImage)
    val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

    asciiTranslator.useNonLinearTranslation()
    asciiTranslator.choiceTable(" :10;+:200;N:45")
    asciiTranslator.translate()
  }

  test("Invalid input of linear translation with custom table on random image.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

      asciiTranslator.useLinearTranslation()
      asciiTranslator.choiceTable("Same symbols in characters string")
      asciiTranslator.translate()
    }
    assert(exception.getMessage == "Symbols in character table are not unique or you have chosen wrong translation mode.")
  }

  test("Invalid input of non-linear translation on random image.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val asciiTranslator = new GreyscaleASCIITranslator(greyscaleTranslator.translate())

      asciiTranslator.useNonLinearTranslation()
      asciiTranslator.choiceTable(" -+")
      asciiTranslator.translate()
    }
    assert(exception.getMessage == "Invalid custom non-linear table input.")
  }
}
