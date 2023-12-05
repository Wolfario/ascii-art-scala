package Filter
import Image.GreyscaleImage

class BrightnessFilter(brightness: Int) extends Filter {

  override def apply(image: GreyscaleImage): GreyscaleImage = {
    val width = image.getSize._2
    val height = image.getSize._1
    val destImage = Array.ofDim[Int](height, width)

    for (x <- 0 until width; y <- 0 until height) {
      var newBrightness = image.getGreyscale(y)(x) + brightness
      if (newBrightness < 0) newBrightness = 0
      if (newBrightness > 255) newBrightness = 255
      destImage(y)(x) = newBrightness
    }
    new GreyscaleImage(height, width, destImage)
  }
}