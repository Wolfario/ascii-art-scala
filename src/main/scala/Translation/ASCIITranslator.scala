package Translation
import java.awt.Color
import Image.Image
import Translation.Type.{Translation, LinearTranslation, NonLinearTranslation}

class ASCIITranslator(Image: Image, ASCIISequence: Seq[Char]) {

  // Linear translation set as default
  private var translationType: Translation = new LinearTranslation()

  def caclulateGreyscale(image: Image): Array[Array[Int]] = {
    image.get match {
      case Some(img) =>
        val width = img.getWidth
        val height = img.getHeight
        val greyscale_array = Array.ofDim[Int](width, height)

        for {
          x <- 0 until width
          y <- 0 until height
        } {
          val pixel = new Color(img.getRGB(x, y))
          val red = pixel.getRed
          val green = pixel.getGreen
          val blue = pixel.getBlue
          val grey: Int = (0.3 * red + 0.59 * green + 0.11 * blue).toInt
          greyscale_array(y)(x) = grey
        }
        greyscale_array

      case None => Array.empty
    }
  }

  def useLinearTranslation(): Unit = translationType = new LinearTranslation()

  def useNonLinearTranslation(): Unit = translationType = new NonLinearTranslation()

}
