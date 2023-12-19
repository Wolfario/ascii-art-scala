package Image

/**
 * Represents a greyscale image with the specified height, width, and pixel values.
 *
 * @param height The height of the greyscale image.
 * @param width The width of the greyscale image.
 * @param greyscaleImage A 2D array representing the pixel values of the greyscale image.
 */
class GreyscaleImage(height: Int, width: Int, greyscaleImage: Array[Array[Int]]) extends Image[Array[Array[Int]]] {

  /**
   * Retrieves a copy of the pixel values of the greyscale image.
   *
   * @return A 2D array representing the pixel values of the greyscale image.
   */
  override def get: Array[Array[Int]] = {
    val copy = Array.ofDim[Int](height, width)
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        copy(i)(j) = greyscaleImage(i)(j)
      }
    }
    copy
  }

  /**
   * Retrieves the size of the greyscale image.
   *
   * @return A tuple containing the height and width of the greyscale image.
   */
  override def getSize: (Int, Int) = (height, width)

  /**
   * Checks if this greyscale image is equal to another greyscale image.
   *
   * @param other Another GreyscaleImage instance to compare with.
   * @return `true` if the images are equal, `false` otherwise.
   */
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
