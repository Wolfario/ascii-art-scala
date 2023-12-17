package Output.ASCII
import Output.Output

trait ASCIIOutput extends Output[Array[Array[Char]]] {

  def output(): Unit

  def set(newImage: Array[Array[Char]]): Unit
}
