package Filter
import Image.GreyscaleImage


/**
 * Represents a filter for flipping a greyscale image along a specified axis.
 *
 * @param axis The axis ('x' or 'y') along which to flip the image.
 */
class FlipFilter(axis: Char) extends Filter {

  /**
   * Applies the flip filter to a greyscale image along the specified axis.
   *
   * @param image The input greyscale image.
   * @return A new greyscale image with flipped pixels along the specified axis.
   * @throws Exception if the provided axis is not 'x' or 'y'.
   */
  override def apply(image: GreyscaleImage): GreyscaleImage = {
    var flipImage: Array[Array[Int]] = image.get

    if (axis.toLower == 'x') {
      flipImage = flipX(image.getSize._2, image.getSize._1, image.get)
    }
    else if (axis.toLower == 'y') {
      flipImage = flipY(image.getSize._2, image.getSize._1, image.get)
    }
    else {
      throw new Exception("Wrong flip axis.")
    }
    new GreyscaleImage(image.getSize._1, image.getSize._2, flipImage)
  }

  /**
   * Flips a greyscale image along the X-axis.
   *
   * @param width  The width of the image.
   * @param height The height of the image.
   * @param image  The input greyscale image.
   * @return A new greyscale image with pixels flipped along the X-axis.
   */
  private def flipX(width: Int, height: Int, image: Array[Array[Int]]): Array[Array[Int]] = {
    val newImage = Array.ofDim[Int](height, width)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        newImage(y)(x) = image(y)(width - x - 1)
    }
  }
    newImage
  }

  /**
   * Flips a greyscale image along the Y-axis.
   *
   * @param width  The width of the image.
   * @param height The height of the image.
   * @param image  The input greyscale image.
   * @return A new greyscale image with pixels flipped along the Y-axis.
   */
  private def flipY(width: Int, height: Int, image: Array[Array[Int]]): Array[Array[Int]] = {
    val newImage = Array.ofDim[Int](height, width)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        newImage(y)(x) = image(height - y - 1)(x)
      }
    }
    newImage
  }
}