package Filter
import Image.GreyscaleImage
import Image.ImageRGB.RandomImage
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class FontFilterTest extends FunSuite {

  test("Applying font filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val fontFilter = new FontFilter((3, 2))
    val filteredGreyscale = fontFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying font filter with all ratio variants in range 10 on RandomImage.") {
    val randomImage = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImage)
    val randomGreyscaleImage = greyscaler.translate()

    for (x <- 1 to 10) {
      for (y <- 1 to 10) {
        val fontFilter = new FontFilter((x, y))
        fontFilter.apply(randomGreyscaleImage)
      }
    }
  }

  test("Invalid applying font filter with negative values on RandomImage.") {
    val randomImage = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImage)
    val randomGreyscaleImage = greyscaler.translate()

    var exception = intercept[Exception] {
      val fontFilter = new FontFilter((-1, 1))
      fontFilter.apply(randomGreyscaleImage)
    }
    assert(exception.getMessage == "Negative font aspect ratio value.")

    exception = intercept[Exception] {
      val fontFilter = new FontFilter((1, -1))
      fontFilter.apply(randomGreyscaleImage)
    }
    assert(exception.getMessage == "Negative font aspect ratio value.")

    exception = intercept[Exception] {
      val fontFilter = new FontFilter((-1, -1))
      fontFilter.apply(randomGreyscaleImage)
    }
    assert(exception.getMessage == "Negative font aspect ratio value.")
  }
}
