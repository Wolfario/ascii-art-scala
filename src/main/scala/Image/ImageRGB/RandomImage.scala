package Image.ImageRGB
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.Random

/**
 * Represents a randomly generated image with a specified height and width.
 *
 * @param height The height of the image.
 * @param width The width of the image.
 */
class RandomImage(height: Int, width: Int) extends ImageRGB {


  private val image: BufferedImage = this.generateImage

  /**
   * Generates a random image with the specified height and width.
   *
   * @return A BufferedImage representing the randomly generated image.
   */
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

  /**
   * Gets a copy of the generated random image.
   *
   * @return A BufferedImage copy of the generated random image.
   */
  override def get: BufferedImage = {
    val copy = new BufferedImage(image.getWidth, image.getHeight, image.getType)
    val g = copy.createGraphics()
    g.drawImage(image, 0, 0, null)
    g.dispose()
    copy
  }

  /**
   * Gets the size of the random image as a tuple of (height, width).
   *
   * @return A tuple representing the height and width of the image.
   */
  override def getSize: (Int, Int) = (height, width)
}
