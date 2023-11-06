package Image
import java.awt.image.BufferedImage

trait Image {

  def get: Option[BufferedImage]

  def getSize: Option[(Int, Int)]
}
