package Filter
import Image.GreyscaleImage
import Import.ImageImporter
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class InvertFilterTest extends FunSuite {
  test("Applying invert filter on empty GreyscaleImage.") {
    val emptyGreyscale = new GreyscaleImage(0, 0, Array.empty)
    val invertFilter = new InvertFilter
    val filteredGreyscale = invertFilter.apply(emptyGreyscale)

    assert(emptyGreyscale.equals(filteredGreyscale))
  }

  test("Applying invert twice in a row.") {
    val importer = new ImageImporter
    val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
    val greyscale = greyscaler.translate()

    val invertFilter = new InvertFilter
    val filteredGreyscale = invertFilter.apply(invertFilter.apply(greyscale))

    assert(greyscale.equals(filteredGreyscale))
  }
}
