package Translation
import Import.ImageImporterImageIO
import org.scalatest.FunSuite


class GreyscaleTranslatorTest extends FunSuite {

  test("Translate black ImageRGB to Greyscale and check if it still black.") {
    val importer = new ImageImporterImageIO()
    val blackImageRGB = importer.loadFrom("testfiles/black_20x20.png")

    val greyscaler = new GreyscaleTranslator(blackImageRGB)
    val translatedBlackImageRGB = greyscaler.translate()

    for (x <- 0 until 20) {
      for (y <- 0 until 20) {
        assert(translatedBlackImageRGB.get(x)(y) == 0)
      }
    }
  }

  test("Translate white ImageRGB to Greyscale and check if it still white.") {
    val importer = new ImageImporterImageIO()
    val whiteImageRGB = importer.loadFrom("testfiles/white_20x20.png")

    val greyscaler = new GreyscaleTranslator(whiteImageRGB)
    val translatedWhiteImageRGB = greyscaler.translate()

    for (x <- 0 until 20) {
      for (y <- 0 until 20) {
        assert(translatedWhiteImageRGB.get(x)(y) == 255)
      }
    }
  }
}
