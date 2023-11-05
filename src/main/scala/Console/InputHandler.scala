package Console
import Image.{Image, ImportedImage, EmptyImage, RandomImage}
import Import.ImageImporter
import Filter.{Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}

class InputHandler {

  private var image: Image = new EmptyImage
  private var filters: List[Filter] = List()
  def handle(args: Array[String]): Option[Boolean] = {

    // It will be possible to get outside the loop only if we do not enter it (No arguments)
    for ((arg, i) <- args.view.zipWithIndex) {
      // Arguments must come in pairs, where the first argument must begin with "--"
      if (arg.length() <= 2 || !(arg.charAt(0) == '-' && arg.charAt(1) == '-')) {
        println("Invalid argument.")
        return None
      }

      val methodName = arg.substring(2)
      methodName match {
        case "image" =>
          if (i == (args.length - 1)) {
            println("Invalid argument.")
            return None
          }

          val imageImporter = new ImageImporter()
          val optionImage: Option[Image] = imageImporter.loadFrom(args.apply(i + 1))
          if (imageImporter.loadFrom(args.apply(i + 1)).isEmpty) {
            println("Invalid argument.")
            return None
          }
          image = optionImage.get
          i += 1
        case "image-random" =>
          // TODO

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
          // TODO

        case "output-file" =>
          // TODO

        case other =>
          println("Invalid argument.")
          return None
      }
    }

    // We need at least one image argument for correct working
    println("No arguments in input.")
    None
  }




}
