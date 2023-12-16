package Import
import Image.ImportedImage
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import javax.imageio.ImageIO

class ImageImporterImageIO() extends ImageImporter {

  def loadFrom(path: String): ImportedImage = {
    try {
      val imageFile: File = new File(path)
      val image: BufferedImage = ImageIO.read(imageFile)
      new ImportedImage(image.getHeight, image.getWidth, image)
    } catch {
      case e: FileNotFoundException =>
        throw new Exception("Can't open the file.")
      case e: IOException =>
        throw new Exception("Can't open the file.")
      case e: NullPointerException =>
        // This exception will be called surely after IOException
        throw new Exception("Can't open the file.")
    }
  }
}
