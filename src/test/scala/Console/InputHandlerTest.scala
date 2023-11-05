package Console
import org.scalatest.FunSuite

class InputHandlerTest extends FunSuite {

  test("No arguments in input") {
    val exception = intercept[Exception] {
      val input = new InputHandler(Array())
    }: Exception
    assert(exception.getMessage() == "No arguments in input.")
  }

  test("Invalid arguments in input") {
    val exception = intercept[Exception] {
      val input = new InputHandler(Array("bzz", "bzz"))
    } : Exception
    assert(exception.getMessage() == "Invalid argument.")
  }
}
