package Image.ImageRGB

import java.awt.image.BufferedImage

class ImportedImage(height: Int, width: Int, image: BufferedImage) extends ImageRGB {


  override def get: BufferedImage = {
    val copy = new BufferedImage(image.getWidth, image.getHeight, image.getType)
    val g = copy.createGraphics()
    g.drawImage(image, 0, 0, null)
    g.dispose()
    copy
  }

  override def getSize: (Int, Int) = (height, width)
}
