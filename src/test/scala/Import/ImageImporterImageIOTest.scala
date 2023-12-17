package Import
import org.scalatest.FunSuite
import Image.ImageRGB.ImageRGB


class ImageImporterImageIOTest extends FunSuite {

  test("Wrong path import") {
    val exception = intercept[Exception] {
      val importer = new ImageImporterImageIO
      val response: ImageRGB = importer.loadFrom("bzz bzz")
    }

    assert(exception.getMessage == "Can't open the file.")
  }

  test("Not an image file opening error") {
    val exception = intercept[Exception] {
      val importer = new ImageImporterImageIO
      val response: ImageRGB = importer.loadFrom("testfiles/not_an_image.png")
    }

    assert(exception.getMessage == "Can't open the file.")
  }

  test("JPG file opening") {
    val importer = new ImageImporterImageIO
    val response: ImageRGB = importer.loadFrom("testfiles/reflexed_cowboys.jpg")
  }

  test("PNG file opening") {
    val importer = new ImageImporterImageIO
    val response: ImageRGB = importer.loadFrom("testfiles/bunny.png")
  }
}
