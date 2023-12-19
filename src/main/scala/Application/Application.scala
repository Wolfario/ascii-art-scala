package Application
import Translation.{GreyscaleASCIITranslator, GreyscaleFilterTranslator, GreyscaleTranslator}
import Filter.Filter
import Image.ImageRGB.{EmptyImage, ImageRGB}
import Output.ASCII.{ASCIIOutput, ASCIIOutputEmpty}

/**
 * The Application class represents an image processing application that can apply filters,
 * convert images to greyscale, and generate ASCII art output.
 */
class Application {

  private var image: ImageRGB = new EmptyImage
  private var output: ASCIIOutput = new ASCIIOutputEmpty
  private var filters: List[Filter] = List()
  private var predefineTableName: String = "standard"
  private var customTable: String = ""
  private var predefineTableUses: Boolean = true
  private var linearTranslation: Boolean = true

  /**
   * Sets the input image for the application.
   *
   * @param newImage The new input image in RGB format.
   */
  def setImage(newImage: ImageRGB): Unit = {
    image = newImage
  }

  /**
   * Sets the list of filters to be applied to the input image.
   *
   * @param newFilters The list of filters to be applied.
   */
  def setFilters(newFilters: List[Filter]): Unit = {
    filters = newFilters
  }

  /**
   * Sets the output mode for the application.
   *
   * @param newOutput The output mode for the application (e.g., ASCII art output).
   */
  def setOutput(newOutput: ASCIIOutput): Unit = {
    output = newOutput
  }

  /**
   * Sets the translation table to be used for ASCII art generation.
   *
   * @param tableInput A tuple indicating whether to use a predefined table and the table name or a custom table.
   */
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

  /**
   * Sets the translation type for ASCII art generation.
   *
   * @param linear A boolean indicating whether to use linear or non-linear translation.
   */
  def setTranslationType(linear: Boolean): Unit = {
    if (linear) {
      linearTranslation = true
    }
    else {
      linearTranslation = false
    }
  }

  /**
   * Handles the image processing and ASCII art generation based on the configured settings.
   */
  def handle(): Unit = {
    if (image.getSize == (0, 0)) {
      throw new Exception("Set image for translation.")
    }

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
}
