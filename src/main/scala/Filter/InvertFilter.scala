package Filter
import Image.{Image, ImportedImage}
import java.awt.image.BufferedImage

class InvertFilter() extends Filter {

  override def apply(image: Image): Image = {
    val width = image.getSize._2
    val height = image.getSize._1

    val invertedImage = new BufferedImage(width, height, image.get.getType)

    for (x <- 0 until width; y <- 0 until height) {
      val pixel = image.get.getRGB(x, y)

      val red = (pixel >> 16) & 0xFF
      val green = (pixel >> 8) & 0xFF
      val blue = pixel & 0xFF

      val invertedRed = 255 - red
      val invertedGreen = 255 - green
      val invertedBlue = 255 - blue

      val invertedPixel = (invertedRed << 16) | (invertedGreen << 8) | invertedBlue
      invertedImage.setRGB(x, y, invertedPixel)
    }

    new ImportedImage(height, width, invertedImage)
  }
}