package Filter
import Image.GreyscaleImage

class FlipFilter(axis: Char) extends Filter {

  override def apply(image: GreyscaleImage): GreyscaleImage = {
    var flipImage: Array[Array[Int]] = image.get

    if (axis.toLower == 'x') {
      flipImage = flipX(image.getSize._2, image.getSize._1, image.get)
    }
    else if (axis.toLower == 'y') {
      flipImage = flipY(image.getSize._2, image.getSize._1, image.get)
    }
    else {
      throw new Exception("Wrong flip axis.")
    }
    new GreyscaleImage(image.getSize._1, image.getSize._2, flipImage)
  }

  private def flipX(width: Int, height: Int, image: Array[Array[Int]]): Array[Array[Int]] = {
    val newImage = Array.ofDim[Int](height, width)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        newImage(y)(x) = image(y)(width - x - 1)
    }
  }
    newImage
  }

  private def flipY(width: Int, height: Int, image: Array[Array[Int]]): Array[Array[Int]] = {
    val newImage = Array.ofDim[Int](height, width)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        newImage(y)(x) = image(height - y - 1)(x)
      }
    }
    newImage
  }
}