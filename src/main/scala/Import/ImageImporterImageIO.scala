package Import
import Image.ImageRGB.ImportedImage
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import javax.imageio.ImageIO

/**
 * An ImageImporter implementation using ImageIO for loading images from file paths.
 */
class ImageImporterImageIO() extends ImageImporter[String] {

  /**
   * Loads an image from the specified file path using ImageIO.
   *
   * @param source The file path of the image to load.
   * @return An ImportedImage object representing the loaded image.
   * @throws Exception If there is an issue opening or reading the file.
   */
  def loadFrom(source: String): ImportedImage = {
    try {
      val imageFile: File = new File(source)
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
