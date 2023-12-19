package Translation

/**
 * A trait defining the interface for translators, which convert one type of data into another.
 */
trait Translator[T] {

  /**
   * Translates the data into the specified type.
   *
   * @return The translated data of type T.
   */
  def translate(): T

}
