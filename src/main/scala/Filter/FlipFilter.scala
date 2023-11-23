package Filter
import Image.{Image, ImportedImage}
import java.awt.image.BufferedImage
class FlipFilter(axis: String) extends Filter {

  override def apply(image: Image): Image = {
    var flipImage: BufferedImage = image.get

    if (axis(0) == 'x' && axis.length == 1) {
      flipImage = flipX(image.getSize._2, image.getSize._1, image)
    }
    else if (axis(0) == 'y' && axis.length == 1) {
      flipImage = flipY(image.getSize._2, image.getSize._1, image)
    }
    else {
      println("Wrong flip axis.")
      // TODO: Exception maybe
    }
    new ImportedImage(image.getSize._1, image.getSize._2, flipImage)
  }

  private def flipX(width: Int, height: Int, image: Image): BufferedImage = {
    val newImage = new BufferedImage (width, height, image.get.getType)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        newImage.setRGB (x, y, image.get.getRGB(width - x - 1, y))
    }
  }
    newImage
  }

  private def flipY(width: Int, height: Int, image: Image): BufferedImage = {
    val newImage = new BufferedImage(width, height, image.get.getType)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        newImage.setRGB(x, height - y - 1, image.get.getRGB(x, y))
      }
    }
    newImage
  }
}