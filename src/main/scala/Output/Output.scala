package Output

trait Output[T] {

  def output(): Unit

  def set(newImage: T): Unit
}
