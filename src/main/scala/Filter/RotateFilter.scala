package Filter
import Image.{Image, ImportedImage}
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage

class RotateFilter(degree: Int) extends Filter {

   override def apply(image: Image): Image = {
    if (degree == 0) {
      image
    }
    else {
      val bufferedImg = image.get.get
      val radians = Math.toRadians(degree)
      val centerX = image.getSize.get._2 / 2
      val centerY = image.getSize.get._1 / 2

      val transform = new AffineTransform()
      transform.rotate(radians, centerX, centerY)

      val op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
      val rotatedImage = new BufferedImage(image.getSize.get._2, image.getSize.get._1, bufferedImg.getType)
      op.filter(bufferedImg, rotatedImage)

      new ImportedImage(rotatedImage.getHeight, rotatedImage.getWidth, rotatedImage)
    }
  }
}