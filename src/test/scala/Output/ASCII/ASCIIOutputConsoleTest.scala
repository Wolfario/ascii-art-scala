package Output.ASCII
import org.scalatest.FunSuite

class ASCIIOutputConsoleTest extends FunSuite {
  test("Output empty instance.") {
    val exception = intercept[Exception] {
      val output = new ASCIIOutputConsole
      output.output()
    }
    assert(exception.getMessage == "Output isn't set.")
  }

  test("Output empty instance after setting it.") {
    val exception = intercept[Exception] {
      val output = new ASCIIOutputConsole
      output.set(Array.empty)
      output.output()
    }
    assert(exception.getMessage == "Output isn't set.")
  }
}
