package Application
import Output.{ImageOutputEmpty, ImageOutput}
import Translation.{GreyscaleASCIITranslator, GreyscaleFilterTranslator, GreyscaleTranslator}
import Image.{EmptyImage, Image}
import Filter.Filter

class Application {

  private var image: Image = new EmptyImage
  private var output: ImageOutput = new ImageOutputEmpty
  private var filters: List[Filter] = List()

  def setImage(newImage: Image): Unit = {
    image = newImage
  }

  def setFilters(newFilters: List[Filter]): Unit = {
    filters = newFilters
  }

  def setOutput(newOutput: ImageOutput): Unit = {
    output = newOutput
  }

  def handle(): Unit = {
    val greyscaleTranslator = new GreyscaleTranslator(image)

    val greyscaleFilterTranslator = new GreyscaleFilterTranslator(greyscaleTranslator.translate(), filters)

    val asciiTranslator = new GreyscaleASCIITranslator(greyscaleFilterTranslator.translate())
    asciiTranslator.choiceKnownTable("standard")
    val asciiArray: Array[Array[Char]] = asciiTranslator.translate()

    output.set(asciiArray)
    output.output()
  }

  private def checkAllModulesPrepared(): Unit = {
    // TODO: Throw exceptions when all modules wasn't set
  }
}
