package Image
import Import.ImageImporterImageIO
import org.scalatest.FunSuite

class ImportedImageTest extends FunSuite {

  test("Comparing two objects of the same file") {
    val importer = new ImageImporterImageIO

    val image1: ImportedImage = importer.loadFrom("testfiles/reflexed_cowboys.jpg")
    val image2: ImportedImage = importer.loadFrom("testfiles/reflexed_cowboys_copy.jpg")

    assert(image1.equals(image2))
    assert(image1.getSize == image2.getSize)
  }

  test("Comparing two objects of different files") {
    val importer = new ImageImporterImageIO

    val image1: ImportedImage = importer.loadFrom("testfiles/reflexed_cowboys.jpg")
    val image2: ImportedImage = importer.loadFrom("testfiles/bunny.png")

    assert(!image1.equals(image2))
    assert(image1.getSize != image2.getSize)
  }
}
