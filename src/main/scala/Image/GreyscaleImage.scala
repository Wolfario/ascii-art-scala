package Image
import java.awt.image.BufferedImage

class GreyscaleImage(height: Int, width: Int, greyscaleImage: Array[Array[Int]]) extends Image {


  override def get: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

  override def getSize: (Int, Int) = (height, width)

  def getGreyscale: Array[Array[Int]] = greyscaleImage

  def equals(other: GreyscaleImage): Boolean = {
    if ((getSize == (0,0)) && (other.getSize == (0,0)))
      return true

    val thisGreyscale = getGreyscale
    val otherGreyscale = other.getGreyscale

    if (thisGreyscale.length != otherGreyscale.length || thisGreyscale(0).length != otherGreyscale(0).length) {
      false
    } else {
      thisGreyscale.indices.forall { i =>
        thisGreyscale(i).indices.forall { j =>
          thisGreyscale(i)(j) == otherGreyscale(i)(j)
        }
      }
    }
  }
}