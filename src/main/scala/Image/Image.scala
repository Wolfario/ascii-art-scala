package Image

trait Image[T] {

  def get: T

  def getSize: (Int, Int)
}
