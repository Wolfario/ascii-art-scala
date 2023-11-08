package Import
import Image.{Image, ImportedImage}
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import javax.imageio.ImageIO

trait ImageImporter {

  /**
   * Creates an Image object from the input path to a specific file.
   * The implementation is similar to formats such as PNG, JPG,
   * so the implementation is in the trait
   * @param path
   * @return Image as an Option
   */
  def loadFrom(path: String): Option[Image] = {
    try {
      val imageFile: File = new File(path)
      val image: BufferedImage = ImageIO.read(imageFile)
      Some(new ImportedImage(image.getHeight, image.getWidth, image))
    } catch {
      case e: FileNotFoundException =>
        println("Wrong image path.")
        None
      case e: IOException =>
        println("Can't open the file.")
        None
      case e: NullPointerException =>
        // This exception will be called surely after IOException
        None
    }
  }

  /**
   * Returns the extension of the
   * file it is working with
   *
   * @param path
   * @return extension as string
   */
  def format: String
}
