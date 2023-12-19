package Translation
import java.awt.Color
import Image.GreyscaleImage
import Image.ImageRGB.ImageRGB

/**
 * Translates an RGB image into a GreyscaleImage by converting each pixel to greyscale.
 *
 * @param image The RGB image to be translated into greyscale.
 */
class GreyscaleTranslator(image: ImageRGB) extends Translator[GreyscaleImage] {

  /**
   * Translates the RGB image into a GreyscaleImage using a weighted average formula.
   *
   * @return The translated GreyscaleImage.
   */
  override def translate(): GreyscaleImage = {
    val width = image.getSize._2
    val height = image.getSize._1
    val greyscale_array = Array.ofDim[Int](height, width)

    for {
      x <- 0 until width
      y <- 0 until height
    } {
      val pixel = new Color(image.get.getRGB(x, y))
      val red = pixel.getRed
      val green = pixel.getGreen
      val blue = pixel.getBlue
      val grey: Int = (0.3 * red + 0.59 * green + 0.11 * blue).toInt
      greyscale_array(y)(x) = grey
    }
    new GreyscaleImage(image.getSize._1, image.getSize._2, greyscale_array)
  }
}
