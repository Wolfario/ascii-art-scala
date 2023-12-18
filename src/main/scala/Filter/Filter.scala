package Filter
import Image.GreyscaleImage

/**
 * Represents a filter of a greyscale image.
 */
trait Filter {

  /**
   * Applies the filter to a greyscale image.
   *
   * @param image The input greyscale image.
   * @return A new greyscale image with applied filter.
   */
  def apply(image: GreyscaleImage): GreyscaleImage
}