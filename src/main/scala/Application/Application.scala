package Application
import Output.{ASCIIOutputEmpty, ASCIIOutput}
import Translation.{GreyscaleASCIITranslator, GreyscaleFilterTranslator, GreyscaleTranslator}
import Image.{EmptyImage, Image}
import Filter.Filter

class Application {

  private var image: Image = new EmptyImage
  private var output: ASCIIOutput = new ASCIIOutputEmpty
  private var filters: List[Filter] = List()
  private var predefineTableName: String = "standard"
  private var customTable: String = ""
  private var predefineTableUses: Boolean = true
  private var linearTranslation: Boolean = true

  def setImage(newImage: Image): Unit = {
    image = newImage
  }

  def setFilters(newFilters: List[Filter]): Unit = {
    filters = newFilters
  }

  def setOutput(newOutput: ASCIIOutput): Unit = {
    output = newOutput
  }

  def setTable(tableInput: (Boolean, String)): Unit = {
    if (tableInput._1) {
      predefineTableUses = true
      predefineTableName = tableInput._2
      customTable = ""
    }
    else {
     predefineTableUses = false
     predefineTableName = "standard"
     customTable = tableInput._2
    }
  }

  def setTranslationType(linear: Boolean): Unit = {
    if (linear) {
      linearTranslation = true
    }
    else {
      linearTranslation = false
    }
  }

  def handle(): Unit = {
    val greyscaleTranslator = new GreyscaleTranslator(image)

    val greyscaleFilterTranslator = new GreyscaleFilterTranslator(greyscaleTranslator.translate(), filters)

    val asciiTranslator = new GreyscaleASCIITranslator(greyscaleFilterTranslator.translate())

    if (linearTranslation) {
      asciiTranslator.useLinearTranslation()
    }
    else {
      asciiTranslator.useNonLinearTranslation()
    }

    if (predefineTableUses) {
      asciiTranslator.choiceKnownTable(predefineTableName)
    }
    else {
      asciiTranslator.choiceTable(customTable)
    }

    val asciiArray: Array[Array[Char]] = asciiTranslator.translate()

    output.set(asciiArray)
    output.output()
  }

  private def checkAllModulesPrepared(): Unit = {
    // TODO: Throw exceptions when all modules wasn't set
  }
}
