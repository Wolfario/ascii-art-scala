package Output.ASCII

class ASCIIOutputConsole() extends ASCIIOutput {

  private var image: Array[Array[Char]] = Array.empty

  override def output(): Unit = {
    if (image.isEmpty)
      throw new Exception("Output isn't set.")

    for (row <- image) {
      for (char <- row) {
        print(char)
      }
      println()
    }
  }

  override def set(newImage: Array[Array[Char]]): Unit = image = newImage
}
