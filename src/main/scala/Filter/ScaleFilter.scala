package Filter
import Image.GreyscaleImage

class ScaleFilter(coefficient: Float) extends Filter {

  override def apply(image: GreyscaleImage): GreyscaleImage = {
    if (coefficient == 1.0) {
      return image
    }
    else if (coefficient == 4.0) {
      return scaleFour(image)
    }
    else if (coefficient == 0.25) {
      return scaleQuarter(image)
    }
    else {
      throw new Exception("Unsupported scale value.")
    }
  }

  private def scaleQuarter(image: GreyscaleImage): GreyscaleImage = {
    val srcWidth = image.getSize._2
    val srcHeight = image.getSize._1
    val destWidth = (srcWidth * 0.25).toInt
    val destHeight = (srcHeight * 0.25).toInt
    val destImage = Array.ofDim[Int](destHeight, destWidth)

    for (x <- 0 until destWidth; y <- 0 until destHeight) {
      val srcX = x * 4
      val srcY = y * 4

      destImage(y)(x) = image.getGreyscale(srcY)(srcX)
    }
    new GreyscaleImage(destHeight, destWidth, destImage)
}

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
        destImage(srcY + i)(srcX + j) = image.getGreyscale(y)(x)
      }
    }
    new GreyscaleImage(destHeight, destWidth, destImage)
  }
}