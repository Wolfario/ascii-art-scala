package Output.ASCII

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

class ASCIIOutputFile(path: String) extends ASCIIOutput {

  if (!Files.exists(Paths.get(path))) {
    Files.createFile(Paths.get(path))
  }

  private var image: Array[Array[Char]] = Array.empty
  private val file = new File(path)

  override def output(): Unit = {
    if (image.isEmpty) {
      throw new Exception("Output isn't set.")
    }

    val writer = new PrintWriter(file)
    for (row <- image) {
      for (char <- row) {
        writer.print(char)
      }
      writer.println()
    }
    writer.close()
  }

  override def set(newImage: Array[Array[Char]]): Unit = image = newImage
}
