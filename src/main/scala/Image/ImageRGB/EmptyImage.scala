package Image.ImageRGB
import java.awt.image.BufferedImage


/**
 * Represents an empty image with no pixels, extending the ImageRGB class.
 */
class EmptyImage extends ImageRGB {

  /**
   * Retrieves a BufferedImage representing an empty image with no pixels.
   *
   * @return An empty BufferedImage with zero width and height and RGB image type.
   */
  override def get: BufferedImage = new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB)

  /**
   * Retrieves the size of the empty image.
   *
   * @return A tuple containing the width and height of the empty image (both set to 0).
   */
  override def getSize: (Int, Int) = (0, 0)

  /**
   * Checks if this empty image is equal to another empty image.
   *
   * @param that Another EmptyImage instance to compare with.
   * @return Always returns `true` since two empty images are considered equal.
   */
  def equals(that: EmptyImage): Boolean = {
    true
  }
}
