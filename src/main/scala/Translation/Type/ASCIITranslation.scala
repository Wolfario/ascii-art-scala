package Translation.Type
import Image.Image

trait Translation {

  def toASCII(greyscaleImage: Array[Array[Int]], characters: List[Char]): Array[Array[Char]]
}
