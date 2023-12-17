package Import
import Image.ImportedImage

trait ImageImporter[T] {

  def loadFrom(source: T): ImportedImage
}
