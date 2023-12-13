package Output

class ImageOutputEmpty extends ImageOutput {

  override def set(newImage: Array[Array[Char]]): Unit = {
    throw new Exception("Cannot use empty output methods. Set correct output.")
  }

  override def output(): Unit = {
    throw new Exception("Cannot use empty output methods. Set correct output.")
  }
}
