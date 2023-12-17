package Import

import Image.ImageRGB.ImportedImage

trait ImageImporter[T] {

  def loadFrom(source: T): ImportedImage
}
