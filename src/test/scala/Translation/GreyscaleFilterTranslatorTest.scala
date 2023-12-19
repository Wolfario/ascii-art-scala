package Translation

import Filter.{BrightnessFilter, Filter, FlipFilter, FontFilter, InvertFilter, RotateFilter, ScaleFilter}
import Image.ImageRGB.RandomImage
import org.scalatest.FunSuite

class GreyscaleFilterTranslatorTest extends FunSuite {
  test("Applying brightness filter on RandomImage and comparing with same filtered image in place.") {
    val randomImageRGB = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImageRGB)
    val randomGreyscaleImageRGB = greyscaler.translate()

    var filters: List[Filter] = List()
    filters = filters :+ (new BrightnessFilter(50))

    val filterer = new GreyscaleFilterTranslator(randomGreyscaleImageRGB, filters)
    val filteredGreyscaleImage = filterer.translate()

    val otherFilter = new BrightnessFilter(50)
    val filteredGreyscaleImageInPlace = otherFilter.apply(randomGreyscaleImageRGB)

    assert(filteredGreyscaleImage.equals(filteredGreyscaleImageInPlace))
  }

  test("Applying flip filter on RandomImage and comparing with same filtered image in place.") {
    val randomImageRGB = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImageRGB)
    val randomGreyscaleImageRGB = greyscaler.translate()

    var filters: List[Filter] = List()
    filters = filters :+ (new FlipFilter('x'))

    val filterer = new GreyscaleFilterTranslator(randomGreyscaleImageRGB, filters)
    val filteredGreyscaleImage = filterer.translate()

    val otherFilter = new FlipFilter('x')
    val filteredGreyscaleImageInPlace = otherFilter.apply(randomGreyscaleImageRGB)

    assert(filteredGreyscaleImage.equals(filteredGreyscaleImageInPlace))
  }

  test("Applying font filter on RandomImage and comparing with same filtered image in place.") {
    val randomImageRGB = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImageRGB)
    val randomGreyscaleImageRGB = greyscaler.translate()

    var filters: List[Filter] = List()
    filters = filters :+ (new FontFilter((2, 3)))

    val filterer = new GreyscaleFilterTranslator(randomGreyscaleImageRGB, filters)
    val filteredGreyscaleImage = filterer.translate()

    val otherFilter = new FontFilter((2, 3))
    val filteredGreyscaleImageInPlace = otherFilter.apply(randomGreyscaleImageRGB)

    assert(filteredGreyscaleImage.equals(filteredGreyscaleImageInPlace))
  }

  test("Applying invert filter on RandomImage and comparing with same filtered image in place.") {
    val randomImageRGB = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImageRGB)
    val randomGreyscaleImageRGB = greyscaler.translate()

    var filters: List[Filter] = List()
    filters = filters :+ (new InvertFilter)

    val filterer = new GreyscaleFilterTranslator(randomGreyscaleImageRGB, filters)
    val filteredGreyscaleImage = filterer.translate()

    val otherFilter = new InvertFilter
    val filteredGreyscaleImageInPlace = otherFilter.apply(randomGreyscaleImageRGB)

    assert(filteredGreyscaleImage.equals(filteredGreyscaleImageInPlace))
  }

  test("Applying rotate filter on RandomImage and comparing with same filtered image in place.") {
    val randomImageRGB = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImageRGB)
    val randomGreyscaleImageRGB = greyscaler.translate()

    var filters: List[Filter] = List()
    filters = filters :+ (new RotateFilter(90))

    val filterer = new GreyscaleFilterTranslator(randomGreyscaleImageRGB, filters)
    val filteredGreyscaleImage = filterer.translate()

    val otherFilter = new RotateFilter(90)
    val filteredGreyscaleImageInPlace = otherFilter.apply(randomGreyscaleImageRGB)

    assert(filteredGreyscaleImage.equals(filteredGreyscaleImageInPlace))
  }

  test("Applying scale filter on RandomImage and comparing with same filtered image in place.") {
    val randomImageRGB = new RandomImage(20, 20)
    val greyscaler = new GreyscaleTranslator(randomImageRGB)
    val randomGreyscaleImageRGB = greyscaler.translate()

    var filters: List[Filter] = List()
    filters = filters :+ (new ScaleFilter(0.25.toFloat))

    val filterer = new GreyscaleFilterTranslator(randomGreyscaleImageRGB, filters)
    val filteredGreyscaleImage = filterer.translate()

    val otherFilter = new ScaleFilter(0.25.toFloat)
    val filteredGreyscaleImageInPlace = otherFilter.apply(randomGreyscaleImageRGB)

    assert(filteredGreyscaleImage.equals(filteredGreyscaleImageInPlace))
  }
}
