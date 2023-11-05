package Console
import Image.Image
import Filter.{Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}

class InputHandler {

  private val filters: List[Filter] = List()
  def handle(args: Array[String]): Boolean = {

    // It will be possible to get outside the loop only if we do not enter it (No arguments)
    for ((arg, i) <- args.view.zipWithIndex) {
      // Arguments must come in pairs, where the first argument must begin with "--"
      if (arg.length() <= 2 || !(arg.charAt(0) == '-' && arg.charAt(1) == '-')) {
        println("Invalid argument.")
        return false
      }

      var methodName = arg.substring(2)
      methodName match {
        case "image" =>
        // TODO

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
          return false
      }
    }

    // We need at least one image argument for correct working
    println("No arguments in input.")
    return false
  }




}
