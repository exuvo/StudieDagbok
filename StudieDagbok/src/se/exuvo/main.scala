package se.exuvo

/**
 * Program för att läsa tidigare dagar och repetera dom
 * Ska endast läsa text fil och automatisk känna av av dag som en viss rad är inskriven efter datum rad
 * Användaren får skriva in ny dag med valfri text editor i botten av filen
 */
object main {
  
  def help(){
    println("noob")
  }
	
  def main(args: Array[String]) = {
    args.foreach(f => 
     f match {
      case "-h" | "--help" => help()
    })
    
    
  }
}