package Import

import Image.ImageRGB.ImportedImage

/**
 * A trait for loading images from file sources.
 */
trait ImageImporter[T] {

  /**
   * Loads an image from the specified source.
   */
  def loadFrom(source: T): ImportedImage
}
