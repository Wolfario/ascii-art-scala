package Image
import java.awt.image.BufferedImage

class GrayscaleImage(height: Int, width: Int, grayscaleImage: Array[Array[Int]]) extends Image {


  override def get: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

  override def getSize: (Int, Int) = (height, width)

  def getGreyscale: Array[Array[Int]] = grayscaleImage
}