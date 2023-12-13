package Translation
import java.awt.Color
import Filter.Filter
import Image.{Image, GreyscaleImage}
import Translation.Type.{LinearASCIITranslation, NonLinearASCIITranslation, ASCIITranslation}

class GreyscaleASCIITranslator(image: GreyscaleImage) extends Translator[Array[Array[Char]]] {

  // Linear translation set as default
  private var translationType: ASCIITranslation = new LinearASCIITranslation()
  private var characters: List[Char] = List()
  private var filters: List[Filter] = List()

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
    var asciiImage = translationType.toASCII(image.getGreyscale, characters)
    asciiImage
  }

  def useLinearTranslation(): Unit = translationType = new LinearASCIITranslation()

  def useNonLinearTranslation(): Unit = translationType = new NonLinearASCIITranslation()

}
