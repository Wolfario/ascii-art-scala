package Output.ASCII
import org.scalatest.FunSuite

class ASCIIOutputEmptyTest extends FunSuite {
  test("Check exception while using set method.") {
    val exception = intercept[Exception] {
      val output = new ASCIIOutputEmpty
      output.set(Array.empty)
    }
    assert(exception.getMessage == "Cannot use empty output methods. Set correct output.")
  }

  test("Check exception while using output method.") {
    val exception = intercept[Exception] {
      val output = new ASCIIOutputEmpty
      output.output()
    }
    assert(exception.getMessage == "Cannot use empty output methods. Set correct output.")
  }
}
