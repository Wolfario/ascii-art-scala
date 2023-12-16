package Translation
import Image.GreyscaleImage
import Translation.Type.{ASCIITranslation, LinearASCIITranslation, NonLinearASCIITranslation}

class GreyscaleASCIITranslator(image: GreyscaleImage) extends Translator[Array[Array[Char]]] {

  // Linear translation set as default
  private var translationType: ASCIITranslation = new LinearASCIITranslation()
  private var input: String = ""

  def choiceKnownTable(table_name: String): Unit = {
    table_name match {
      case "standard" =>
        input = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
      case "ten_levels" =>
        input = " .:-=+*#%@"
      case _ =>
        throw new Exception("Unknown character table.")
    }
  }

  def choiceTable(funcInput: String): Unit = {
    if (funcInput.isEmpty) {
      throw new Exception("Empty character table is not valid table.")
    }

    input = funcInput
  }

  def translate(): Array[Array[Char]] = {
    val asciiImage = translationType.toASCII(image.getGreyscale, input)
    asciiImage
  }

  def useLinearTranslation(): Unit = translationType = new LinearASCIITranslation()

  def useNonLinearTranslation(): Unit = translationType = new NonLinearASCIITranslation()
}
