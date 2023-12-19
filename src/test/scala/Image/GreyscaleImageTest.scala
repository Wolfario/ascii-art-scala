package Image
import Image.ImageRGB.ImportedImage
import Import.ImageImporterImageIO
import Translation.GreyscaleTranslator
import org.scalatest.FunSuite

class GreyscaleImageTest extends FunSuite {
  test("Comparing two objects of the same file") {
    val importer = new ImageImporterImageIO

    val image1: ImportedImage = importer.loadFrom("testfiles/reflexed_cowboys.jpg")
    val image2: ImportedImage = importer.loadFrom("testfiles/reflexed_cowboys_copy.jpg")

    val greyscaleTranslatorImage1 = new GreyscaleTranslator(image1)
    val greyscaleTranslatorImage2 = new GreyscaleTranslator(image2)

    val greyscaleImage1 = greyscaleTranslatorImage1.translate()
    val greyscaleImage2 = greyscaleTranslatorImage2.translate()

    assert(greyscaleImage1.equals(greyscaleImage2))
    assert(greyscaleImage1.getSize == greyscaleImage2.getSize)
  }

  test("Comparing two objects of different files") {
    val importer = new ImageImporterImageIO

    val image1: ImportedImage = importer.loadFrom("testfiles/small_art.png")
    val image2: ImportedImage = importer.loadFrom("testfiles/bunny.png")

    val greyscaleTranslatorImage1 = new GreyscaleTranslator(image1)
    val greyscaleTranslatorImage2 = new GreyscaleTranslator(image2)

    val greyscaleImage1 = greyscaleTranslatorImage1.translate()
    val greyscaleImage2 = greyscaleTranslatorImage2.translate()

    assert(!greyscaleImage1.equals(greyscaleImage2))
    assert(greyscaleImage1.getSize != greyscaleImage2.getSize)
  }
}
