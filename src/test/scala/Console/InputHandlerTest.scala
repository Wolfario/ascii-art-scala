package Console
import org.scalatest.FunSuite
import scala.util.{Try, Success, Failure}

class InputHandlerTest extends FunSuite {

  test("No arguments in input.") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array())
    }

    assert(exception.getMessage == "No arguments in input.")
  }

  test("Invalid arguments in input") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("bzz bzz"))
    }

    assert(exception.getMessage == "Invalid argument.")
  }

  test("Invalid arguments in input with correct inputs.") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image-random", "bzz bzz"))
    }

    assert(exception.getMessage == "Invalid argument.")
  }

  test("Input without output.") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg"))
    }

    assert(exception.getMessage == "Operations were carried out without output.")
  }

  test("Output without input.") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--output-file", "testfiles/output.txt"))
    }

    assert(exception.getMessage == "No arguments in input.")
  }

  test("Wrong image path.") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/i_wasnt_exist_never.nono", "--output-file", "testfiles/output.txt"))
    }

    assert(exception.getMessage == "Can't open the file.")
  }


  test("Wrong output path.") {
    val input = new InputHandler()

    // It will create "created_file.txt" if file will not be found
    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--output-file", "testfiles/created_file.txt"))
  }

  test("Repeating filters.") {
    val input = new InputHandler()

    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--invert", "--invert", "--output-file", "testfiles/created_file.txt"))

    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--rotate", "-90", "--brightness", "+35", "--invert", "--brightness", "+35", "--output-file", "testfiles/created_file.txt"))
  }

  test("Invalid rotate value.") {
    var exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--rotate", "25", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Rotate value is not multiples of 90.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--rotate", "-25", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Rotate value is not multiples of 90.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--rotate", "++90", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid rotate value.")

    val input = new InputHandler()
    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--rotate", "36000", "--output-file", "testfiles/created_file.txt"))
  }

  test("Invalid brightness value.") {
    var exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--brightness", "++50", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid brightness value.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--brightness", "not a number", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid brightness value.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--brightness", "5.5", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid brightness value.")

    val input = new InputHandler()
    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--brightness", "+255", "--output-file", "testfiles/created_file.txt"))
  }

  test("Invalid scale value.") {
    var exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--scale", "not a number", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid scale value.")

    val input = new InputHandler()
    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--scale", "0.25", "--output-file", "testfiles/created_file.txt"))

    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--scale", "1", "--output-file", "testfiles/created_file.txt"))

    input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--scale", "4", "--output-file", "testfiles/created_file.txt"))
  }
}
