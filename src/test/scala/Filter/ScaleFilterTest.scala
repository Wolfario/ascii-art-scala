package Filter
import Image.GreyscaleImage
import Import.ImageImporterImageIO
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
    val importer = new ImageImporterImageIO
    val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
    val greyscale = greyscaler.translate()

    val scaleFilterUp = new ScaleFilter(4)
    val scaleFilterDown = new ScaleFilter(0.25.toFloat)
    val filteredGreyscale = scaleFilterDown.apply(scaleFilterUp.apply(greyscale))

    assert(greyscale.getSize == filteredGreyscale.getSize)
  }

  test("Applying scale filter with all available values.") {
    val importer = new ImageImporterImageIO
    val greyscaler = new GreyscaleTranslator(importer.loadFrom("testfiles/bunny.png"))
    val greyscale = greyscaler.translate()

    val scaleFilterUp = new ScaleFilter(4)
    val scaleFilterDown = new ScaleFilter(0.25.toFloat)
    val scaleFilterSame = new ScaleFilter(1)

    var filteredResult = scaleFilterSame.apply(greyscale)
    assert(greyscale.getSize == filteredResult.getSize)

    filteredResult = scaleFilterUp.apply(greyscale)
    val upTuple = (greyscale.getSize._1 * 4, greyscale.getSize._2 * 4)
    assert(upTuple == filteredResult.getSize)

    filteredResult = scaleFilterDown.apply(greyscale)
    val downTuple = (greyscale.getSize._1 * 0.25, greyscale.getSize._2 * 0.25)
    assert(downTuple == filteredResult.getSize)
  }
}
