package Image

/**
 * A trait representing an image with the specified height, width, and pixel values.
 */
trait Image[T] {

  /**
   * Retrieves a copy of the pixel values of the image type T.
   *
   * @return Something of type T representing the pixel values of the image.
   */
  def get: T

  /**
   * Retrieves the image size.
   *
   * @return A tuple containing the height and width of the image.
   */
  def getSize: (Int, Int)
}
