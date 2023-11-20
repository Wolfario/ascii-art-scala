package Output

class ImageOutputFile() extends ImageOutput {

  private var image: Array[Array[Char]] = Array.empty

  override def output(): Unit = ???

  override def output(path: String): Unit = {

  }

  def set(newImage: Array[Array[Char]]): Unit = image = newImage
}
