package Image
import java.awt.image.BufferedImage
import java.awt.Color
import java.util.Random

class RandomImage(height: Int, width: Int) extends Image {

  private val image: BufferedImage = this.generateImage

  private def generateImage: BufferedImage = {
    val returnImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val random = new Random()

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val red = random.nextInt(256) // Random red component (0-255)
        val green = random.nextInt(256) // Random green component (0-255)
        val blue = random.nextInt(256) // Random blue component (0-255)

        val rgb = new Color(red, green, blue).getRGB

        returnImage.setRGB(x, y, rgb)
      }
    }
    returnImage
  }
  override def get: Option[BufferedImage] = Some(generateImage)

  override def getSize: Option[(Int, Int)] = Some((height, width))



}
