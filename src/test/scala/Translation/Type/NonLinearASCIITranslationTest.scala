package Translation.Type
import Image.ImageRGB.RandomImage
import Import.ImageImporterImageIO
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class NonLinearASCIITranslationTest extends FunSuite {

  test("Valid input with correct custom table on RandomImage.") {
    val randomImage = new RandomImage(150, 150)
    val greyscaleTranslator = new GreyscaleTranslator(randomImage)
    val nonLinearASCIITranslator = new NonLinearASCIITranslation
    val result = nonLinearASCIITranslator.toASCII(greyscaleTranslator.translate().get, " :200;+:50;N:5")

    assert(result.length == randomImage.getSize._2)
  }

  test("Invalid input with incorrect custom table, that sum isn't equal 255 on RandomImage.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val nonLinearASCIITranslator = new NonLinearASCIITranslation
      nonLinearASCIITranslator.toASCII(greyscaleTranslator.translate().get, " :200;+:50")
    }

    assert(exception.getMessage == "Sum of non-linear table indexes does not equal 255.")
  }

  test("Invalid input with incorrect custom table, that have incorrect format on RandomImage.") {
    val exception = intercept[Exception] {
      val randomImage = new RandomImage(150, 150)
      val greyscaleTranslator = new GreyscaleTranslator(randomImage)
      val nonLinearASCIITranslator = new NonLinearASCIITranslation
      nonLinearASCIITranslator.toASCII(greyscaleTranslator.translate().get, "Incorrect format")
    }

    assert(exception.getMessage == "Invalid custom non-linear table input.")
  }

  test("Check if black image will translate into one single symbol that we used.") {
    val importer = new ImageImporterImageIO
    val greyscaleTranslator = new GreyscaleTranslator(importer.loadFrom("testfiles/black_150x150.png"))
    val nonLinearTranslator = new NonLinearASCIITranslation
    val result = nonLinearTranslator.toASCII(greyscaleTranslator.translate().get, "-:1;+:254")

    for (i <- 0 until 150) {
      for (j <- 0 until 150) {
        assert(result(i)(j) == '-')
      }
    }
  }

  test("Check if white image will translate into one single symbol that we used.") {
    val importer = new ImageImporterImageIO
    val greyscaleTranslator = new GreyscaleTranslator(importer.loadFrom("testfiles/white_150x150.png"))
    val nonLinearTranslator = new NonLinearASCIITranslation
    val result = nonLinearTranslator.toASCII(greyscaleTranslator.translate().get, "-:254;+:1")

    for (i <- 0 until 150) {
      for (j <- 0 until 150) {
        assert(result(i)(j) == '+')
      }
    }
  }
}
