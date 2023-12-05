package Image
import java.awt.image.BufferedImage

class SignImage(height: Int, width: Int, image: BufferedImage, signImage: Array[Array[Char]]) extends Image {


  override def get: BufferedImage = image

  override def getSize: (Int, Int) = (height, width)

  def getSign: Array[Array[Char]] = signImage
}