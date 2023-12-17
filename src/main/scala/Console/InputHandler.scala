package Console
import Import.ImageImporterImageIO
import Filter.{BrightnessFilter, Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}
import Output.ASCII.{ASCIIOutput, ASCIIOutputConsole, ASCIIOutputEmpty, ASCIIOutputFile}
import _root_.Image.ImageRGB.{EmptyImage, ImageRGB, ImportedImage, RandomImage}

class InputHandler {

  private var imageRGB: ImageRGB = new EmptyImage
  private var filters: List[Filter] = List()
  private var output: ASCIIOutput = new ASCIIOutputEmpty
  private var predefineTableName: String = "standard"
  private var customTableInput: String = ""
  private var predefineTableUses: Boolean = true
  private var linearTranslation: Boolean = true
  def handle(args: Array[String]): Unit = {
    // We need to skip method values if it needs. Initially we are waiting for method name (reason of initial true value)
    var mainArgument: Boolean = true
    var emptyCheck: Boolean = false
    var outputCheck: Boolean = false

    // It will be possible to get outside the loop only if we do not enter it (No arguments)
    for ((arg, i) <- args.view.zipWithIndex) {
      if (mainArgument) {
        // Arguments must come in pairs, where the first argument must begin with "--"
        if (arg.length() <= 2 || !(arg.charAt(0) == '-' && arg.charAt(1) == '-')) {
          throw new Exception("Invalid argument.")
        }

      val methodName = arg.substring(2)
        methodName match {
          case "image" =>
            emptyCheck = true
            nextParameterExistsCheck(i, args)

            val imageImporter = new ImageImporterImageIO()
            val optionImage: ImportedImage = imageImporter.loadFrom(args.apply(i + 1))
            imageRGB = optionImage
            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "image-random" =>
            emptyCheck = true
            val default_height = 384
            val default_width = 512

            imageRGB = new RandomImage(default_height, default_width)
          case "rotate" =>
            nextParameterExistsCheck(i, args)

            var rotateInput: String = args.apply(i + 1)
            var sign = '+'

            if (rotateInput(0) == '+' || rotateInput(0) == '-') {
              if (rotateInput.length == 1) {
                throw new Exception("Invalid rotate value.")
              }
              sign = rotateInput(0)
              rotateInput = rotateInput.drop(1)
            }
            else if (!rotateInput(0).isDigit) {
              throw new Exception("Invalid rotate value.")
            }

            for (char <- rotateInput) {
              if (!char.isDigit) {
                throw new Exception("Invalid rotate value.")
              }
            }

            var degree = rotateInput.toInt % 360
            if (sign == '-') {
              degree = 360 - degree
            }

            val newFilter = new RotateFilter(degree)
            filters = filters :+ newFilter
            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "invert" =>
            val newFilter = new InvertFilter()
            filters = filters :+ newFilter
          case "flip" =>
            nextParameterExistsCheck(i, args)

            val newFilter = new FlipFilter(args.apply(i + 1))
            filters = filters :+ newFilter

            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "scale" =>
            nextParameterExistsCheck(i, args)

            try {
              val newFilter = new ScaleFilter(args.apply(i + 1).toFloat)
              filters = filters :+ newFilter
            } catch {
              case e: Exception =>
                throw new Exception("Invalid scale value.")
            }

            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "brightness" =>
            nextParameterExistsCheck(i, args)

            var brightnessInput: String = args.apply(i + 1)
            var sign = '+'

            if (brightnessInput(0) == '+' || brightnessInput(0) == '-') {
              if (brightnessInput.length == 1) {
                throw new Exception("Invalid brightness value.")
              }
              sign = brightnessInput(0)
              brightnessInput = brightnessInput.drop(1)
            }
            else if (!brightnessInput(0).isDigit) {
              throw new Exception("Invalid brightness value.")
            }

            for (char <- brightnessInput) {
              if (!char.isDigit) {
                throw new Exception("Invalid brightness value.")
              }
            }

            var brightness = brightnessInput.toInt
            if (sign == '-') {
              brightness = 0 - brightness
            }

            val newFilter = new BrightnessFilter(brightness)
            filters = filters :+ newFilter
            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "font-aspect-ratio" =>
            nextParameterExistsCheck(i, args)

            try {
              val ratio = args.apply(i + 1)
              val keyValuePairs = ratio.split(":")

              if (keyValuePairs.length != 2) {
                throw new Exception()
              }

              val fontAspectRatio = (keyValuePairs(1).toInt, keyValuePairs(0).toInt)
              val newFilter = new FontFilter(fontAspectRatio)
              filters = filters :+ newFilter
              // In next iteration will be method value, so we need to skip it
              mainArgument = false
            } catch {
              case e: Exception =>
                throw new Exception("Invalid aspect ratio value.")
            }
          case "output-console" =>
            outputCheck = true
            output = new ASCIIOutputConsole()
          case "output-file" =>
            nextParameterExistsCheck(i, args)

            outputCheck = true
            val path = args.apply(i + 1)
            try {
              output = new ASCIIOutputFile(path)
            } catch {
              case e: Exception =>
                throw new Exception("Invalid output path.")
            }
            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "table" =>
            nextParameterExistsCheck(i, args)

            predefineTableUses = true
            predefineTableName = args.apply(i + 1)
            customTableInput = ""

            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "custom-table" =>
            nextParameterExistsCheck(i, args)

            predefineTableUses = false
            predefineTableName = "standard"
            customTableInput = args.apply(i + 1)

            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "use-linear" =>
            linearTranslation = true
          case "use-non-linear" =>
            linearTranslation = false
          case other =>
            throw new Exception("Invalid argument.")
        }
      }
      else
        mainArgument = true
    }

    // We need at least one image argument for correct working
    if (!emptyCheck) {
      throw new Exception("No arguments in input.")
    }

    if (!outputCheck) {
      throw new Exception("Operations were carried out without output.")
    }
  }

  def getImage: ImageRGB = imageRGB

  def getFilters: List[Filter] = filters

  def getOutput: ASCIIOutput = output

  def getTable: (Boolean, String) = {
    if (predefineTableUses) {
      return (true, predefineTableName)
    }
    (false, customTableInput)
  }

  def getTranslationType: Boolean = linearTranslation

  private def nextParameterExistsCheck(i: Int, args: Array[String]): Unit = {
    if (i == (args.length - 1)) {
      throw new Exception("Invalid argument.")
    }
  }

}
