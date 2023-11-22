package Filter
import Image.Image

abstract class Filter {

  def apply(image: Image): Image
}