package Import
import Image.ImportedImage

trait ImageImporter {

  def loadFrom(path: String): ImportedImage
}
