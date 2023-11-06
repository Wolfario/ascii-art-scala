package Image
import Image.Image
import java.awt.image.BufferedImage

class ImportedImage(height: Int, width: Int, image: BufferedImage) extends Image {


  override def get: Option[BufferedImage] = Some(image)

  override def getSize: Option[(Int, Int)] = Some((height, width))
}
