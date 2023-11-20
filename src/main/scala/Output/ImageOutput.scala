package Output
import Image.Image

trait ImageOutput {

  private var image: Array[Array[Char]] = Array.empty

  def output(): Unit

  def set(newImage: Array[Array[Char]]): Unit
}
