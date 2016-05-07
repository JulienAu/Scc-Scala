
import scala.collection.mutable.ListBuffer
import sun.security.util.Length

class Graph[T] (
  val sommets: ListBuffer[Sommet[T]] = ListBuffer[Sommet[T]](),
  val arretes: ListBuffer[Arrete[T]] = ListBuffer[Arrete[T]]()    
) {
  
  def addSommets (ts: String*) {
    ts.map { t => t.split("/").foreach {x => sommets.+=(new Sommet(x.split("->")(0)))}}
    ts.map { t => t.split("/").foreach { t => if (t.split("->").length > 1 ) t.split("->")(1).split(",").foreach { x => this.connectSommet(sommets.apply((t.split("->")(0).toInt)-1) , sommets.apply((x.toInt)-1)) } }
  }}
  
  def connectSommet (n1: Sommet[T], n2: Sommet[T]) {
    if (n1.eq(n2)) throw new Exception("Les sommets doivent être différents (" + n1 + ", " + n2 + ")!")
    arretes.+=(new Arrete(n1, n2))
  }
  
}
