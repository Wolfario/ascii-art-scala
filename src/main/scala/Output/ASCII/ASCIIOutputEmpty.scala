package Output.ASCII

class ASCIIOutputEmpty extends ASCIIOutput {

  override def set(newImage: Array[Array[Char]]): Unit = {
    throw new Exception("Cannot use empty output methods. Set correct output.")
  }

  override def output(): Unit = {
    throw new Exception("Cannot use empty output methods. Set correct output.")
  }
}
