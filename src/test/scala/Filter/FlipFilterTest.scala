package Filter
import Image.GreyscaleImage
import Import.ImageImporter
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class FlipFilterTest extends FunSuite {

  test("Applying flip filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val flipFilter = new FlipFilter("x")
    val filteredGreyscale = flipFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying flip filter with wrong axis value.") {
    var exception = intercept[Exception] {
      val importer = new ImageImporter
      val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
      val greyscale = greyscaler.translate()

      val flipFilter = new FlipFilter("xyz")
      val filteredGreyscale = flipFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Wrong flip axis.")

    exception = intercept[Exception] {
      val importer = new ImageImporter
      val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
      val greyscale = greyscaler.translate()

      val flipFilter = new FlipFilter(" ")
      val filteredGreyscale = flipFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Wrong flip axis.")
  }
}
