package Output.ASCII
import org.scalatest.FunSuite
import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

class ASCIIOutputFileTest extends FunSuite {
  test("Output empty instance.") {
    val exception = intercept[Exception] {
      val output = new ASCIIOutputFile("testfiles/output.txt")
      output.output()
    }
    assert(exception.getMessage == "Output isn't set.")
  }

  test("Output empty instance after setting it.") {
    val exception = intercept[Exception] {
      val output = new ASCIIOutputFile("testfiles/output.txt")
      output.set(Array.empty)
      output.output()
    }
    assert(exception.getMessage == "Output isn't set.")
  }

  test("Check if file will be create, after calling.") {
    val path = "testfiles/new_file.txt"
    Files.deleteIfExists(Paths.get(path))

    assert(!Files.exists(Paths.get(path)))
    val output = new ASCIIOutputFile(path)
    assert(Files.exists(Paths.get(path)))
  }
}
