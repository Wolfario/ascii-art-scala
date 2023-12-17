package Image.ImageRGB

import org.scalatest.FunSuite

class RandomImageTest extends FunSuite{

  test("Comparing two random images of different sizes") {
    val randomImage1 = new RandomImage(50, 50)
    val randomImage2 = new RandomImage(100, 100)

    assert(!randomImage1.equals(randomImage2))
    assert(randomImage1.getSize != randomImage2.getSize)
  }

  test("Comparing two random images of same size (hope it won't generate same image)") {
    val randomImage1 = new RandomImage(50, 50)
    val randomImage2 = new RandomImage(50, 50)

    assert(!randomImage1.equals(randomImage2))
    assert(randomImage1.getSize == randomImage2.getSize)
  }
}

