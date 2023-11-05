package Console
import org.scalatest.FunSuite

class InputHandlerTest extends FunSuite {

  test("No arguments in input") {
    val input = new InputHandler()
    assert(input.handle(Array()).isEmpty)
  }

  test("Invalid arguments in input") {
    val input = new InputHandler()
    assert(input.handle(Array("bzz", "bzz")).isEmpty)
  }
}
