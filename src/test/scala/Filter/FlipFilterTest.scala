package Filter
import Image.GreyscaleImage
import Image.ImageRGB.RandomImage
import Import.ImageImporterImageIO
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class FlipFilterTest extends FunSuite {

  test("Applying flip filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val flipFilter = new FlipFilter('x')
    val filteredGreyscale = flipFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying flip filter on RandomImage with X axis.") {
    val randomImage = new GreyscaleImage(0, 0, Array.empty)

    val flipFilterLower = new FlipFilter('x')
    val filteredRandomImageLower = flipFilterLower.apply(randomImage)

    val flipFilterUpper = new FlipFilter('X')
    val filteredRandomImageUpper = flipFilterUpper.apply(randomImage)
  }

  test("Applying flip filter on RandomImage with Y axis.") {
    val randomImage = new GreyscaleImage(0, 0, Array.empty)

    val flipFilterLower = new FlipFilter('y')
    val filteredRandomImageLower = flipFilterLower.apply(randomImage)

    val flipFilterUpper = new FlipFilter('Y')
    val filteredRandomImageUpper = flipFilterUpper.apply(randomImage)
  }

  test("Applying flip filter with wrong axis value.") {
    var exception = intercept[Exception] {
      val importer = new ImageImporterImageIO
      val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
      val greyscale = greyscaler.translate()

      val flipFilter = new FlipFilter('u')
      val filteredGreyscale = flipFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Wrong flip axis.")

    exception = intercept[Exception] {
      val importer = new ImageImporterImageIO
      val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
      val greyscale = greyscaler.translate()

      val flipFilter = new FlipFilter(' ')
      val filteredGreyscale = flipFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Wrong flip axis.")
  }
}
