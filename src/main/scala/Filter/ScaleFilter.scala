package Filter
import Image.GreyscaleImage

/**
 * A filter for scaling a GreyscaleImage by a specified coefficient.
 *
 * @param coefficient The scaling coefficient. Supported values: 1.0 (no change), 4.0 (scale up by 4 times), 0.25 (scale down by 4 times).
 * @throws Exception if the provided coefficient is not supported.
 */
class ScaleFilter(coefficient: Float) extends Filter {

  /**
   * Applies scaling to the given GreyscaleImage based on the specified coefficient.
   *
   * @param image The input GreyscaleImage to be scaled.
   * @return A new GreyscaleImage with the applied scaling.
   * @throws Exception if the provided coefficient is not supported.
   */
  override def apply(image: GreyscaleImage): GreyscaleImage = {
    if (coefficient == 1.0) {
      image
    }
    else if (coefficient == 4.0) {
      scaleFour(image)
    }
    else if (coefficient == 0.25) {
      scaleQuarter(image)
    }
    else {
      throw new Exception("Unsupported scale value.")
    }
  }

  /**
   * Scales down the given GreyscaleImage by a factor of 4.
   *
   * @param image The input GreyscaleImage to be scaled down.
   * @return A new GreyscaleImage with the applied scaling.
   */
  private def scaleQuarter(image: GreyscaleImage): GreyscaleImage = {
    val srcWidth = image.getSize._2
    val srcHeight = image.getSize._1
    val destWidth = (srcWidth * 0.25).toInt
    val destHeight = (srcHeight * 0.25).toInt
    val destImage = Array.ofDim[Int](destHeight, destWidth)

    for (x <- 0 until destWidth; y <- 0 until destHeight) {
      val srcX = x * 4
      val srcY = y * 4

      destImage(y)(x) = image.get(srcY)(srcX)
    }
    new GreyscaleImage(destHeight, destWidth, destImage)
}

  /**
   * Scales up the given GreyscaleImage by a factor of 4.
   *
   * @param image The input GreyscaleImage to be scaled up.
   * @return A new GreyscaleImage with the applied scaling.
   */
  private def scaleFour(image: GreyscaleImage): GreyscaleImage = {
    val srcWidth = image.getSize._2
    val srcHeight = image.getSize._1
    val destWidth = srcWidth * 4
    val destHeight = srcHeight * 4
    val destImage = Array.ofDim[Int](destHeight, destWidth)

    for (x <- 0 until srcWidth; y <- 0 until srcHeight) {
      val srcX = x * 4
      val srcY = y * 4

      for (i <- 0 until 4; j <- 0 until 4) {
        destImage(srcY + i)(srcX + j) = image.get(y)(x)
      }
    }
    new GreyscaleImage(destHeight, destWidth, destImage)
  }
}