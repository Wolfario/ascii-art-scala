package Image.ImageRGB
import Image.Image
import java.awt.image.BufferedImage

/**
 * A trait representing an RGB image with the specified height, width, and pixel values.
 */
trait ImageRGB extends Image[BufferedImage] {

  /**
   * Checks whether this RGB image is equal to another ImageRGB.
   *
   * @param that The other ImageRGB to compare with.
   * @return true if the images are equal, false otherwise.
   */
  def equals(that: ImageRGB): Boolean = {
    val thisImage = get
    val otherImage = that.get

    if (thisImage.getWidth != otherImage.getWidth || thisImage.getHeight != otherImage.getHeight) {
      false
    } else {
      (0 until thisImage.getWidth).forall { i =>
        (0 until thisImage.getHeight).forall { j =>
          thisImage.getRGB(i, j) == otherImage.getRGB(i, j)
        }
      }
    }
  }
}
