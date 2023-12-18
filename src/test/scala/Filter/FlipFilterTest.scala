package Filter
import Image.GreyscaleImage
import Image.ImageRGB.RandomImage
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
    val randomImage = new RandomImage(150, 150)
    val greyscaler = new GreyscaleTranslator(randomImage)
    val randomGreyscaleImage = greyscaler.translate()

    val flipFilterLower = new FlipFilter('x')
    val filteredRandomImageLower = flipFilterLower.apply(randomGreyscaleImage)

    val flipFilterUpper = new FlipFilter('X')
    val filteredRandomImageUpper = flipFilterUpper.apply(randomGreyscaleImage)
  }

  test("Applying flip filter on RandomImage with Y axis.") {
    val randomImage = new RandomImage(150, 150)
    val greyscaler = new GreyscaleTranslator(randomImage)
    val randomGreyscaleImage = greyscaler.translate()

    val flipFilterLower = new FlipFilter('y')
    val filteredRandomImageLower = flipFilterLower.apply(randomGreyscaleImage)

    val flipFilterUpper = new FlipFilter('Y')
    val filteredRandomImageUpper = flipFilterUpper.apply(randomGreyscaleImage)
  }

  test("Applying flip filter with wrong axis value.") {
    var exception = intercept[Exception] {
      val greyscaler = new GreyscaleTranslator(new RandomImage(150, 150))
      val greyscale = greyscaler.translate()

      val flipFilter = new FlipFilter('u')
      val filteredGreyscale = flipFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Wrong flip axis.")

    exception = intercept[Exception] {
      val greyscaler = new GreyscaleTranslator(new RandomImage(150, 150))
      val greyscale = greyscaler.translate()

      val flipFilter = new FlipFilter(' ')
      val filteredGreyscale = flipFilter.apply(greyscale)
    }

    assert(exception.getMessage == "Wrong flip axis.")
  }
}
