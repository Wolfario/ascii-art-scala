package Filter
import Image.GreyscaleImage
import Import.ImageImporter
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class ScaleFilterTest extends FunSuite {

  test("Applying scale filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val scaleFilter = new ScaleFilter(4)
    val filteredGreyscale = scaleFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying scale filter twice to 4.0 and 0.25 (compare sizes).") {
    val importer = new ImageImporter
    val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
    val greyscale = greyscaler.translate()

    val scaleFilterUp = new ScaleFilter(4)
    val scaleFilterDown = new ScaleFilter(0.25.toFloat)
    val filteredGreyscale = scaleFilterDown.apply(scaleFilterUp.apply(greyscale))

    assert(greyscale.getSize == filteredGreyscale.getSize)
  }
}
