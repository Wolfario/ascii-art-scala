package Application
import Image.ImageRGB.RandomImage
import Output.ASCII.ASCIIOutputFile
import org.scalatest.FunSuite

class ApplicationTest extends FunSuite {
  test("Invalid applying handle without set input.") {
    val exception = intercept[Exception] {
      val app = new Application
      app.handle()
    }
    assert(exception.getMessage == "Set image for translation.")
  }

  test("Invalid applying handle without set output.") {
    val exception = intercept[Exception] {
      val app = new Application
      app.setImage(new RandomImage(20, 20))
      app.handle()
    }
    assert(exception.getMessage == "Cannot use empty output methods. Set correct output.")
  }

  test("Pass correct linear translation of RandomImage to file.") {
    val app = new Application
    app.setImage(new RandomImage(20, 20))
    app.setTable((true, "ten_levels"))
    app.setTranslationType(false)
    app.setTranslationType(true)
    app.setOutput(new ASCIIOutputFile("testfiles/output.txt"))
    app.handle()
  }
}
