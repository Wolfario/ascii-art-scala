package Output

trait ASCIIOutput {

  def output(): Unit

  def set(newImage: Array[Array[Char]]): Unit
}
