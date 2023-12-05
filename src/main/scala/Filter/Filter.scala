package Filter
import Image.SignImage

trait Filter {

  def apply(image: SignImage): SignImage
}