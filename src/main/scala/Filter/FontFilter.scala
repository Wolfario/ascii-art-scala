package Filter
import Image.GreyscaleImage

/**
 * A filter for adjusting the aspect ratio of a GreyscaleImage to match a specified font aspect ratio.
 *
 * @param fontAspectRatio A tuple representing the desired font aspect ratio as (fontHeight, fontWidth).
 * @throws Exception if the provided font aspect ratio contains negative values.
 */
class FontFilter(fontAspectRatio: (Int, Int)) extends Filter {

  /**
   * Applies the font aspect ratio adjustment to the given GreyscaleImage.
   *
   * @param image The input GreyscaleImage to be transformed.
   * @return A new GreyscaleImage with the adjusted aspect ratio based on the specified font aspect ratio.
   */
  override def apply(image: GreyscaleImage): GreyscaleImage = {
    if (fontAspectRatio._1 <= 0 || fontAspectRatio._2 <= 0) {
      throw new Exception("Negative font aspect ratio value.")
    }

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
        newGreyscaleImage(newI)(j) = image.get(i)(j)
      }
    }

    new GreyscaleImage(newHeight, width, newGreyscaleImage)
  }
}
