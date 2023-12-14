package Image
import org.scalatest.FunSuite

class EmptyImageTest extends FunSuite {

  test("Comparing two objects") {
    val emptyImage1 = new EmptyImage
    val emptyImage2 = new EmptyImage

    assert(emptyImage1.equals(emptyImage2))
  }

  test("Default size is 0x0") {
    val emptyImage = new EmptyImage

    assert(emptyImage.getSize == (0, 0))
  }
}
