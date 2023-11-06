package Image
import Image.Image

import java.awt.image.BufferedImage

class RandomImage(height: Int, width: Int) extends Image {

  override def get: Option[BufferedImage] = ??? // TODO

  override def getSize: Option[(Int, Int)] = Some((height, width))
}
