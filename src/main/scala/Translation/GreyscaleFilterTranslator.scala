package Translation
import Filter.Filter
import Image.GreyscaleImage

/**
 * Translates a GreyscaleImage by applying a series of filters to modify its content.
 *
 * @param image   The GreyscaleImage to be translated.
 * @param filters A list of Filter objects to be applied sequentially to the image.
 */
class GreyscaleFilterTranslator(image: GreyscaleImage, filters: List[Filter]) extends Translator[GreyscaleImage] {

  /**
   * Translates the GreyscaleImage by applying a series of filters.
   *
   * @return The translated GreyscaleImage after applying all specified filters.
   */
  override def translate(): GreyscaleImage = {
    var resultImage = image
    for (filter <- filters) {
      resultImage = filter.apply(resultImage)
    }
    resultImage
  }
}
