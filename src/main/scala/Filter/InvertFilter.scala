package Filter
import Image.GreyscaleImage

class InvertFilter() extends Filter {

  override def apply(image: GreyscaleImage): GreyscaleImage = {
    val width = image.getSize._2
    val height = image.getSize._1

    val invertedImage = Array.ofDim[Int](height, width)
    val grayscale = image.getGreyscale

    for (x <- 0 until width; y <- 0 until height) {
      val pixel = grayscale(y)(x)

      val invertedPixel = 255 - pixel
      invertedImage(y)(x) = invertedPixel
    }

    new GreyscaleImage(height, width, invertedImage)
  }
}