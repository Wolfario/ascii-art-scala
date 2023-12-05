package Filter
import Image.{Image, ImportedImage}
import java.awt.image.BufferedImage

class ScaleFilter(coefficient: Float) extends Filter {

  override def apply(image: Image): Image = {
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

  private def scaleQuarter(image: Image): Image = {
    val srcWidth = image.getSize._2
    val srcHeight = image.getSize._1
    val destWidth = (srcWidth * 0.25).toInt
    val destHeight = (srcHeight * 0.25).toInt
    val destImage = new ImportedImage(destHeight, destWidth, new BufferedImage(destWidth, destHeight, image.get.getType))

    for (x <- 0 until destWidth; y <- 0 until destHeight) {
      val srcX = x * 4
      val srcY = y * 4

      val srcColor = image.get.getRGB(srcX, srcY)
      destImage.get.setRGB(x, y, srcColor)
    }
    destImage
  }

  private def scaleFour(image: Image): Image = {
    val srcWidth = image.getSize._2
    val srcHeight = image.getSize._1
    val destWidth = srcWidth * 4
    val destHeight = srcHeight * 4
    val destImage = new ImportedImage(destHeight, destWidth, new BufferedImage(destWidth, destHeight, image.get.getType))

    for (x <- 0 until srcWidth; y <- 0 until srcHeight) {
      val srcX = x * 4
      val srcY = y * 4
      val srcColor = image.get.getRGB(x, y)

      for (i <- 0 until 4; j <- 0 until 4) {
        destImage.get.setRGB(srcX + i, srcY + j, srcColor)
      }
    }
    destImage
  }
}