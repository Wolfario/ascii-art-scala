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
}
