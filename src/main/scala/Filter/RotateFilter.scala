package Filter
import Image.GreyscaleImage

/**
 * A filter for rotating a GreyscaleImage by a specified degree.
 *
 * @param degree The angle by which to rotate the image. Must be a multiple of 90.
 * @throws Exception if the provided degree is not a multiple of 90.
 */
class RotateFilter(degree: Int) extends Filter {

  if ((degree % 90) != 0) {
    throw new Exception("Rotate value is not multiples of 90.")
  }

  /**
   * Applies rotation to the given GreyscaleImage based on the specified degree.
   *
   * @param image The input GreyscaleImage to be rotated.
   * @return A new GreyscaleImage with the applied rotation.
   */
  override def apply(image: GreyscaleImage): GreyscaleImage = {
     val grayscale = image.get
     val width = image.getSize._2
     val height = image.getSize._1

     degree match {
       case 90 | -270 =>
         val rotatedImage = Array.ofDim[Int](width, height)
         for (i <- 0 until height; j <- 0 until width) {
           rotatedImage(j)(height - 1 - i) = grayscale(i)(j)
         }
         new GreyscaleImage(width, height, rotatedImage)
       case -90 | 270 =>
         val rotatedImage = Array.ofDim[Int](width, height)
         for (i <- 0 until height; j <- 0 until width) {
           rotatedImage(width - 1 - j)(i) = grayscale(i)(j)
         }
         new GreyscaleImage(width, height, rotatedImage)
       case 180 | -180 =>
         val rotatedImage = Array.ofDim[Int](height, width)
         for (i <- 0 until height; j <- 0 until width) {
           rotatedImage(height - 1 - i)(width - 1 - j) = grayscale(i)(j)
         }
         new GreyscaleImage(height, width, rotatedImage)
       case _ => image
     }
  }
}