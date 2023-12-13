package Output

trait ImageOutput {

  def output(): Unit

  def set(newImage: Array[Array[Char]]): Unit
}
