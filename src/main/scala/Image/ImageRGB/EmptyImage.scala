package Image.ImageRGB
import java.awt.image.BufferedImage


class EmptyImage extends ImageRGB {

  override def get: BufferedImage = new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB)

  override def getSize: (Int, Int) = (0, 0)

  def equals(that: EmptyImage): Boolean = {
    true
  }
}
