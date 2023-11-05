package Console
import Image.Image
import Filter.{Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}

class InputHandler(args: Array[String]) {

  // We need at least one image argument for correct working
  if (args.isEmpty) {
    throw new Exception("No arguments in input.")
  }

  private val filters: List[Filter] = List()

    for (arg <- args) {
      // Arguments must come in pairs, where the first argument must begin with "--"
      if (arg.length() <= 2 || !(arg[0] != '-' || arg[1] != '-')) {
        throw new Exception("Invalid argument.")
      }

      // TODO
    }
}
