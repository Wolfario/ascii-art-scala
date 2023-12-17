package Filter
import Image.GreyscaleImage

class FontFilter(fontAspectRatio: (Int, Int)) extends Filter {

  override def apply(image: GreyscaleImage): GreyscaleImage = {
    val (fontHeight, fontWidth) = fontAspectRatio
    val (height, width) = image.getSize
    val newHeight = height * fontHeight / fontWidth
    val newGreyscaleImage = Array.ofDim[Int](newHeight, width)

    for {
      i <- 0 until height
      j <- 0 until width
    } {
      val newI = i * fontHeight / fontWidth
      if (newI < newHeight) {
        newGreyscaleImage(newI)(j) = image.getGreyscale(i)(j)
      }
    }

    new GreyscaleImage(newHeight, width, newGreyscaleImage)
  }
}
