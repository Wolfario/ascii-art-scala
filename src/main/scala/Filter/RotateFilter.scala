package Filter
import Image.GreyscaleImage
class RotateFilter(degree: Int) extends Filter {

  if ((degree % 90) != 0) {
    throw new Exception("Rotate value is not multiples of 90.")
  }

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