package Console
import org.scalatest.FunSuite

class InputHandlerTest extends FunSuite {

  test("Initial object getter value of image is 0x0 image") {
    val input = new InputHandler()
    assert(input.getImage.getSize == (0, 0))
  }

  test("Initial object getter value of filters is empty list") {
    val input = new InputHandler()
    assert(input.getFilters == List())
  }

  test("Initial object getter value of output is ASCIIOutputEmpty class") {
    val exception = intercept[Exception] {
      val input = new InputHandler()
      input.getOutput.output()
    }
    assert(exception.getMessage == "Cannot use empty output methods. Set correct output.")
  }

  test("Initial object getter value of predefine table is standard") {
    val input = new InputHandler()
    assert(input.getTable._2 == "standard")
  }

  test("Initial object getter value of translation type is linear (true value of private linearTranslation)") {
    val input = new InputHandler()
    assert(input.getTranslationType)
  }

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
  }

  test("Invalid scale value.") {
    var exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--scale", "not a number", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid scale value.")
  }

  test("Invalid font value.") {
    var exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--font-aspect-ratio", "junk input", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid aspect ratio value.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--font-aspect-ratio", "x:1", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid aspect ratio value.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--font-aspect-ratio", "1:y", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid aspect ratio value.")

    exception = intercept[Exception] {
      val input = new InputHandler()
      input.handle(Array("--image", "testfiles/reflexed_cowboys.jpg", "--font-aspect-ratio", "x:y", "--output-file", "testfiles/created_file.txt"))
    }

    assert(exception.getMessage == "Invalid aspect ratio value.")
  }

  test("Valid inputs with brightness filter") {
    val input = new InputHandler()
    for (brightness <- -255 to 255) {
      input.handle(Array("--image-random", "--brightness", brightness.toString, "--output-file", "testfiles/created_file.txt"))
    }
  }

  test("Valid inputs with flip filter") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--flip", "x", "--output-file", "testfiles/created_file.txt"))
    input.handle(Array("--image-random", "--flip", "y", "--output-file", "testfiles/created_file.txt"))
  }

  test("Valid inputs with font filter") {
    val input = new InputHandler()
    for (x <- 1 to 8) {
      for(y <- 1 to 8) {
        input.handle(Array("--image-random", "--font-aspect-ratio", x.toString.concat(":").concat(y.toString), "--output-file", "testfiles/created_file.txt"))
      }
    }
  }

  test("Valid inputs with invert filter") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--invert", "--output-file", "testfiles/created_file.txt"))
  }

  test("Valid inputs with rotate filter") {
    val input = new InputHandler()
    for (m <- 1 to 4) {
      input.handle(Array("--image-random", "--rotate", (m * 90).toString, "--output-file", "testfiles/created_file.txt"))
      input.handle(Array("--image-random", "--rotate", "+".concat((m * 90).toString), "--output-file", "testfiles/created_file.txt"))
      input.handle(Array("--image-random", "--rotate", "-".concat((m * 90).toString), "--output-file", "testfiles/created_file.txt"))
    }
  }

  test("Valid inputs with scale filter") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--scale", "1", "--output-file", "testfiles/created_file.txt"))
    input.handle(Array("--image-random", "--scale", "0.25", "--output-file", "testfiles/created_file.txt"))
    input.handle(Array("--image-random", "--scale", "4", "--output-file", "testfiles/created_file.txt"))
  }

  test("Valid inputs with predefined table") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--table", "ten_levels", "--output-file", "testfiles/created_file.txt"))
  }

  test("Valid input with custom table") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--custom-table", " -+", "--output-file", "testfiles/created_file.txt"))
  }

  test("Valid input with custom non-linear table") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--custom-table", " :100;-:50;+:75", "--use-non-linear", "--output-file", "testfiles/created_file.txt"))
  }

  test("Valid input with custom table after changing back from non-linear") {
    val input = new InputHandler()
    input.handle(Array("--image-random", "--table", "ten_levels", "--use-non-linear", "--use-linear", "--output-file", "testfiles/created_file.txt"))
  }
}
