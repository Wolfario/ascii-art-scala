package Output
import Image.Image

trait ImageOutput[T] {

  private var imageASCII: Option[String] = None

  def output(path: T): Unit

  def set(textImage: String): Unit = {
    imageASCII = Some(textImage)
  }
}
