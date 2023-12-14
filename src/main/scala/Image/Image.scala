package Image
import java.awt.image.BufferedImage

trait Image {

  def get: BufferedImage

  def getSize: (Int, Int)

  def equals(that: Image): Boolean = {
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
