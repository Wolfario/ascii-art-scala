package Console
import Image.{EmptyImage, Image, ImportedImage, RandomImage}
import Import.ImageImporter
import Filter.{BrightnessFilter, Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}
import Output.{ImageOutput, ImageOutputConsole, ImageOutputFile}

class InputHandler {

  private var image: Image = new EmptyImage
  private var filters: List[Filter] = List()
  private var output: Option[ImageOutput] = None
  def handle(args: Array[String]): Option[Boolean] = {
    // We need to skip method values if it needs. Initially we are waiting for method name (reason of initial true value)
    var mainArgument: Boolean = true
    var emptyCheck: Boolean = false
    var outputCheck: Boolean = false

    // It will be possible to get outside the loop only if we do not enter it (No arguments)
    for ((arg, i) <- args.view.zipWithIndex) {
      if (mainArgument) {
        // Arguments must come in pairs, where the first argument must begin with "--"
        if (arg.length() <= 2 || !(arg.charAt(0) == '-' && arg.charAt(1) == '-')) {
          println("Invalid argument.")
          return None
        }

      val methodName = arg.substring(2)
        methodName match {
          case "image" =>
            emptyCheck = true
            if (i == (args.length - 1)) {
              println("Invalid argument.")
              return None
            }

            val imageImporter = new ImageImporter()
            val optionImage: Option[ImportedImage] = imageImporter.loadFrom(args.apply(i + 1))
            if (imageImporter.loadFrom(args.apply(i + 1)).isEmpty) {
              println("Invalid argument.")
              return None
            }
            image = optionImage.get
            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "image-random" =>
            emptyCheck = true
            var default_height = 384
            var default_width = 512

            image = new RandomImage(default_height, default_width)
          case "rotate" =>
            if (i == (args.length - 1)) {
              println("Invalid argument.")
              return None
            }

            var rotateInput: String = args.apply(i + 1)
            var sign = '+'

            if (rotateInput(0) == '+' || rotateInput(0) == '-') {
              if (rotateInput.length == 1) {
                println("Invalid rotate value.")
                return None
              }
              sign = rotateInput(0)
              rotateInput = rotateInput.drop(1)
            }
            else if (!rotateInput(0).isDigit) {
              println("Invalid rotate value.")
              return None
            }

            for (char <- rotateInput) {
              if (!char.isDigit) {
                println("Invalid rotate value.")
                return None
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
            if (i == (args.length - 1)) {
              println("Invalid argument.")
              return None
            }

            val newFilter = new FlipFilter(args.apply(i + 1))
            filters = filters :+ newFilter

            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "scale" =>
            if (i == (args.length - 1)) {
              println("Invalid argument.")
              return None
            }

            val newFilter = new ScaleFilter(args.apply(i + 1).toFloat)
            filters = filters :+ newFilter

            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case "brightness" =>
            if (i == (args.length - 1)) {
              println("Invalid argument.")
              return None
            }

            var brightnessInput: String = args.apply(i + 1)
            var sign = '+'

            if (brightnessInput(0) == '+' || brightnessInput(0) == '-') {
              if (brightnessInput.length == 1) {
                println("Invalid brightness value.")
                return None
              }
              sign = brightnessInput(0)
              brightnessInput = brightnessInput.drop(1)
            }
            else if (!brightnessInput(0).isDigit) {
              println("Invalid brightness value.")
              return None
            }

            for (char <- brightnessInput) {
              if (!char.isDigit) {
                println("Invalid brightness value.")
                return None
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
            // TODO

          case "output-console" =>
            outputCheck = true
            output = Some(new ImageOutputConsole())
          case "output-file" =>
            if (i == (args.length - 1)) {
              println("Invalid argument.")
              return None
            }
            outputCheck = true
            val path = args.apply(i + 1)
            try {
              output = Some(new ImageOutputFile(path))
            } catch {
              case e: Exception =>
                println("Invalid output path.")
                return None
            }
            // In next iteration will be method value, so we need to skip it
            mainArgument = false
          case other =>
            println("Invalid argument.")
            return None
        }
      }
      else
        mainArgument = true
    }

    if (!outputCheck) {
      println("Operations were carried out without output.")
    }

    // We need at least one image argument for correct working
    if (!emptyCheck) {
      println("No arguments in input.")
      None
    }
    Some(true)
  }

  def getImage: Image = image

  def getFilters: List[Filter] = filters

  def getOutput: Option[ImageOutput] = output

}
