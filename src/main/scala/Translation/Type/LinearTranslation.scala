package Translation.Type
import Image.Image

class LinearTranslation extends Translation {

  override def toASCII(greyscaleImage: Array[Array[Int]], characters: List[Char]): Array[Array[Char]] = {
    var range = 255 / characters.length
    val asciiReturn: Array[Array[Char]] = greyscaleImage.map {
      row =>
        row.map {
          pixelValue =>
            val char_index = (pixelValue / range).floor.toInt
            characters(char_index)
      }
    }
    asciiReturn
  }
}
