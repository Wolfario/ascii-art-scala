package Image
import java.awt.image.BufferedImage

trait Image {

  def get: BufferedImage

  def getSize: (Int, Int)
}
