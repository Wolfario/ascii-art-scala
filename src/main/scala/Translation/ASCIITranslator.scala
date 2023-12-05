package Translation
import java.awt.Color
import Filter.Filter
import Image.{Image, GrayscaleImage}
import Translation.Type.{LinearTranslation, NonLinearTranslation, Translation}

class ASCIITranslator(Image: Image) {

  // Linear translation set as default
  private var translationType: Translation = new LinearTranslation()
  private var characters: List[Char] = List()
  private var filters: List[Filter] = List()

  private def calculateGreyscale(image: Image): Array[Array[Int]] = {
      val width = image.getSize._2
      val height = image.getSize._1
      val greyscale_array = Array.ofDim[Int](height, width)

      for {
        x <- 0 until width
        y <- 0 until height
      } {
        val pixel = new Color(image.get.getRGB(x, y))
        val red = pixel.getRed
        val green = pixel.getGreen
        val blue = pixel.getBlue
        val grey: Int = (0.3 * red + 0.59 * green + 0.11 * blue).toInt
        greyscale_array(y)(x) = grey
      }
      greyscale_array
  }

  def choiceKnownTable(table_name: String): Boolean = {
    characters = List()

    table_name match {
      case "standard" =>
        var table = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
        for (char <- table) {
          characters = characters :+ char
        }
        true
      case _ =>
        println("Unknown character table")
        false
    }
  }

  def choiceTable(table: String): Boolean = {
    characters = List()
    for (char <- table) {
      characters = characters :+ char
    }
    true
  }

  def setFilters(inFilters: List[Filter]): Unit = filters = inFilters

  def translate(): Array[Array[Char]] = {
    var greyscaleImage = new GrayscaleImage(Image.getSize._1, Image.getSize._2, this.calculateGreyscale(Image))
    for (filter <- filters) {
      greyscaleImage = filter.apply(greyscaleImage)
    }
    var asciiImage = translationType.toASCII(greyscaleImage.getGreyscale, characters)
    asciiImage
  }

  def useLinearTranslation(): Unit = translationType = new LinearTranslation()

  def useNonLinearTranslation(): Unit = translationType = new NonLinearTranslation()

}
