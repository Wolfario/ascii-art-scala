package Output

class ImageOutputConsole() extends ImageOutput[Unit] {

  private var imageASCII: Option[String] = None

  override def output(path: Unit): Unit = {
    println(imageASCII.get)
  }
}
