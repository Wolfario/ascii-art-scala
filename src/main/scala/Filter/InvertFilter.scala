package Filter
import Image.GreyscaleImage

/**
 * A filter for inverting the colors of a GreyscaleImage.
 */
class InvertFilter() extends Filter {

  /**
   * Applies color inversion to the given GreyscaleImage.
   *
   * @param image The input GreyscaleImage to be color-inverted.
   * @return A new GreyscaleImage with inverted colors.
   */
  override def apply(image: GreyscaleImage): GreyscaleImage = {
    val width = image.getSize._2
    val height = image.getSize._1

    val invertedImage = Array.ofDim[Int](height, width)
    val grayscale = image.get

    for (x <- 0 until width; y <- 0 until height) {
      val pixel = grayscale(y)(x)

      val invertedPixel = 255 - pixel
      invertedImage(y)(x) = invertedPixel
    }

    new GreyscaleImage(height, width, invertedImage)
  }
}