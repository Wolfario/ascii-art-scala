package Output

class ASCIIOutputConsole() extends ASCIIOutput {

  private var image: Array[Array[Char]] = Array.empty

  override def output(): Unit = {
    for (row <- image) {
      for (char <- row) {
        print(char)
      }
      println()
    }
  }

  override def set(newImage: Array[Array[Char]]): Unit = image = newImage
}
