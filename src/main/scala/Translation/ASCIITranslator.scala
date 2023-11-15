package Translation
import Image.Image
import Translation.Type.{Translation, LinearTranslation, NonLinearTranslation}

class ASCIITranslator(Image: Image) {

  // Linear translation set as default
  private var translationType: Translation = new LinearTranslation()
}
