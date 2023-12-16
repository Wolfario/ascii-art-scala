package Filter
import Image.GreyscaleImage
import Import.ImageImporterImageIO
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class RotateFilterTest extends FunSuite {
  test("Applying rotate filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val rotateFilter = new RotateFilter(90)
    val filteredGreyscale = rotateFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying rotate filter with 360 degree (checking equality).") {
    val importer = new ImageImporterImageIO
    val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
    val greyscale = greyscaler.translate()

    val rotateFilter = new RotateFilter(360)
    val filteredGreyscale = rotateFilter.apply(greyscale)

    assert(greyscale.equals(filteredGreyscale))
  }

  test("Applying rotate filter with value that is not multiples of 90.") {
    val exception = intercept[Exception] {
      val importer = new ImageImporterImageIO
      val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
      val greyscale = greyscaler.translate()
      val rotateFilter = new RotateFilter(10)
      val filteredGreyscale = rotateFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Rotate value is not multiples of 90.")
  }
}
