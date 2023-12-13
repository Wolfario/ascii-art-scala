package Translation
import Filter.Filter
import Image.GreyscaleImage

class GreyscaleFilterTranslator(image: GreyscaleImage, filters: List[Filter]) extends Translator[GreyscaleImage] {

  override def translate(): GreyscaleImage = {
    var resultImage = image
    for (filter <- filters) {
      resultImage = filter.apply(resultImage)
    }
    resultImage
  }
}
