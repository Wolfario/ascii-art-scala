package Output
import Image.Image

trait ImageOutput {

  private var imageASCII: Option[String] = None

  def output(): Unit

  def output(path: String): Unit

  def set(textImage: String): Unit = {
    imageASCII = Some(textImage)
  }
}
