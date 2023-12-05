package Filter
import Image.GrayscaleImage

trait Filter {

  def apply(image: GrayscaleImage): GrayscaleImage
}