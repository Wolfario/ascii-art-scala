package Image

class GreyscaleImage(height: Int, width: Int, greyscaleImage: Array[Array[Int]]) extends Image[Array[Array[Int]]] {

  override def get: Array[Array[Int]] = greyscaleImage

  override def getSize: (Int, Int) = (height, width)

  def equals(other: GreyscaleImage): Boolean = {
    if ((getSize == (0,0)) && (other.getSize == (0,0)))
      return true

    val thisGreyscale = get
    val otherGreyscale = other.get

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
