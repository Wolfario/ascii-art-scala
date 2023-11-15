package Output
import Image.Image

trait ImageOutput[T] {
  def output(path: T): Unit = {

  }
}
