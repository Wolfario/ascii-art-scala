package Image.ImageRGB
import java.awt.image.BufferedImage

/**
 * Represents an imported image with the specified height, width, and a BufferedImage instance.
 *
 * @param height The height of the imported image.
 * @param width The width of the imported image.
 * @param image A BufferedImage representing the pixel values of the imported image.
 */
class ImportedImage(height: Int, width: Int, image: BufferedImage) extends ImageRGB {


  /**
   * Retrieves a copy of the pixel values of the imported image as a BufferedImage.
   *
   * @return A BufferedImage containing a copy of the pixel values of the imported image.
   */
  override def get: BufferedImage = {
    val copy = new BufferedImage(image.getWidth, image.getHeight, image.getType)
    val g = copy.createGraphics()
    g.drawImage(image, 0, 0, null)
    g.dispose()
    copy
  }

  /**
   * Retrieves the size of the imported image.
   *
   * @return A tuple containing the height and width of the imported image.
   */
  override def getSize: (Int, Int) = (height, width)
}
