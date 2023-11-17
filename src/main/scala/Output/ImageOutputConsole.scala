package Output

class ImageOutputConsole() extends ImageOutput {

  private var imageASCII: Option[String] = None

  override def output(): Unit = {
    println(imageASCII.get)
  }

  override def output(path: String): Unit = ???
}
