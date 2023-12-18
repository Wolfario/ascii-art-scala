package Filter
import Image.GreyscaleImage
import org.scalatest.FunSuite

class BrightnessFilterTest extends FunSuite {

  test("Applying brightness filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val brightnessFilter = new BrightnessFilter(150)
    val filteredGreyscale = brightnessFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying brightness filter with all valid inputs.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    for (brightness <- -255 to 255) {
      val brightnessFilter = new BrightnessFilter(brightness)
      val filteredGreyscale = brightnessFilter.apply(emptyGreyscale)
    }
  }
}
