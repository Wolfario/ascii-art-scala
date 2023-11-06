package Image
import Image.Image
import java.awt.image.BufferedImage


class EmptyImage extends Image {

  override def get: Option[BufferedImage] = None

  override def getSize: Option[(Int, Int)] = None
}
