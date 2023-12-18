package Filter
import Image.GreyscaleImage

/**
 * Represents a filter to adjust the brightness of a greyscale image.
 *
 * @param brightness The amount by which to adjust the brightness. Positive values increase brightness, negative values decrease it.
 */
class BrightnessFilter(brightness: Int) extends Filter {

  /**
   * Applies the brightness filter to a greyscale image.
   *
   * @param image The input greyscale image.
   * @return A new greyscale image with adjusted brightness.
   */
  override def apply(image: GreyscaleImage): GreyscaleImage = {
    val width = image.getSize._2
    val height = image.getSize._1
    val destImage = Array.ofDim[Int](height, width)

    for (x <- 0 until width; y <- 0 until height) {
      var newBrightness = image.get(y)(x) + brightness
      if (newBrightness < 0) newBrightness = 0
      if (newBrightness > 255) newBrightness = 255
      destImage(y)(x) = newBrightness
    }
    new GreyscaleImage(height, width, destImage)
  }
}