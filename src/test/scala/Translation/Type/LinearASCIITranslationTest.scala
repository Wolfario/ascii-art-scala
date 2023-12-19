package Translation.Type
import Image.ImageRGB.RandomImage
import Import.ImageImporterImageIO
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class LinearASCIITranslationTest extends FunSuite {

  test("Valid input with unique symbols on RandomImage.") {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val linearASCIITranslator = new LinearASCIITranslation
      val result = linearASCIITranslator.toASCII(greyscaleTranslator.translate().get, " -+")

      assert(result.length == randomImage.getSize._2)
  }

  test("Invalid input with non unique symbols on RandomImage.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val linearASCIITranslator = new LinearASCIITranslation
      linearASCIITranslator.toASCII(greyscaleTranslator.translate().get, "---")
    }
    assert(exception.getMessage == "Symbols in character table are not unique or you have chosen wrong translation mode.")
  }

  test("Check if black image will translate into one single symbol that we used.") {
    val importer = new ImageImporterImageIO
    val greyscaleTranslator = new GreyscaleTranslator(importer.loadFrom("testfiles/black_150x150.png"))
    val linearTranslator = new LinearASCIITranslation
    val result = linearTranslator.toASCII(greyscaleTranslator.translate().get, "-+")

    for (i <- 0 until 150) {
      for (j <- 0 until 150) {
        assert(result(i)(j) == '-')
      }
    }
  }

  test("Check if white image will translate into one single symbol that we used.") {
    val importer = new ImageImporterImageIO
    val greyscaleTranslator = new GreyscaleTranslator(importer.loadFrom("testfiles/white_150x150.png"))
    val linearTranslator = new LinearASCIITranslation
    val result = linearTranslator.toASCII(greyscaleTranslator.translate().get, "-+")

    for (i <- 0 until 150) {
      for (j <- 0 until 150) {
        assert(result(i)(j) == '+')
      }
    }
  }
}
