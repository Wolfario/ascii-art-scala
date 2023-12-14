package Image
import java.awt.image.BufferedImage

class ImportedImage(height: Int, width: Int, image: BufferedImage) extends Image {


  override def get: BufferedImage = image

  override def getSize: (Int, Int) = (height, width)
}
