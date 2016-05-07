import scala.collection.mutable.Stack
import scala.collection.mutable.ListBuffer

class Tarjan[T] (graph: Graph[T]) {

  private val cfgs = ListBuffer[ListBuffer[Sommet[T]]]()
  private val P = Stack[Sommet[T]]()
  private var numCourant = 0
  
  def Cfgs (): ListBuffer[ListBuffer[Sommet[T]]] = {
    graph.sommets.map { sommet => 
      if (!sommet.visite) tarjan(sommet)
    }
    cfgs
  }
  
  private def tarjan (sommet: Sommet[T]) {
    sommet.init(numCourant)
    P.push(sommet)
    numCourant += 1
    
    Voisins(sommet, graph.arretes).map { voisin =>  
      if (graph.sommets.contains(voisin) && !voisin.visite) {
        tarjan(voisin)
        if (voisin.numAccessible < sommet.numAccessible) sommet.numAccessible = voisin.numAccessible
       
      } else if (P.contains(voisin)) {
        if (voisin.numAccessible < sommet.num) sommet.numAccessible = voisin.num
      }
    }
    if (sommet.estRacine()) {
      cfgs.+=(ajoutCFG(sommet))
    }
  }
  
  private def ajoutCFG (sommet: Sommet[T]): ListBuffer[Sommet[T]] = {
    val CFG = ListBuffer[Sommet[T]]()
    while (true) {
      val courant = P.pop
      CFG.+=(courant)
      if (courant.eq(sommet)) return CFG
    }
    CFG
  }
  
  private def Voisins (sommet: Sommet[T], arretes: ListBuffer[Arrete[T]]): ListBuffer[Sommet[T]] = {
      arretes
        .filter { arrete => arrete.source.eq(sommet)}
        .map { arrete => if (arrete.source.eq(sommet)) arrete.target else arrete.source }
  }
  
}