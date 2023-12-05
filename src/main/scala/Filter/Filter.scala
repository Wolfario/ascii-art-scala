package Filter
import Image.GreyscaleImage

trait Filter {

  def apply(image: GreyscaleImage): GreyscaleImage
}