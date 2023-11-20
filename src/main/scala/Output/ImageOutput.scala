package Output
import Image.Image

trait ImageOutput {

  private var image: Array[Array[Char]] = Array.empty

  def output(): Unit

  def output(path: String): Unit

  def set(newImage: Array[Array[Char]]): Unit
}
