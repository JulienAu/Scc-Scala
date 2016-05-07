

class Sommet [T] (obj: String) {
  
  var num: Int = -1
  var numAccessible: Int = -2
  var visite = false
  
  def init (numCourant: Int) {
    num = numCourant
    numAccessible = numCourant
    visite = true
  }
  
  def numAccessibleMAJ (valeur: Int) {
    if (valeur < numAccessible) numAccessible = valeur
  }
  
  def estRacine (): Boolean = {
    numAccessible == num
  }
  
  override def toString (): String = {
    "S[" + obj.toString + "]"
  }
} 