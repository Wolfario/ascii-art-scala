package Console
import Image.{Image, ImportedImage, EmptyImage, RandomImage}
import Import.ImageImporter
import Filter.{Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}
import Output.{ImageOutput, ImageOutputFile, ImageOutputConsole}

class InputHandler {

  private var image: Image = new EmptyImage
  private var filters: List[Filter] = List()
  private var output: Option[ImageOutput[_]] = None
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
            // TODO

          case "invert" =>
            // TODO

          case "flip" =>
            // TODO

          case "brightness" =>
            // TODO

          case "font-aspect-ratio" =>
            // TODO

          case "output-console" =>
            outputCheck = true
            output = Some(new ImageOutputConsole())
          case "output-file" =>
            outputCheck = true
            output = Some(new ImageOutputFile())
          case other =>
            println("Invalid argument.")
            return None
        }
      }
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

  def getOutput: Option[ImageOutput[_]] = output


}
