package se.exuvo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

/**
 * Program för att läsa tidigare dagar och repetera dom
 * Ska endast läsa text fil och automatisk känna av av dag som en viss rad är inskriven efter datum rad
 * Användaren får skriva in ny dag med valfri text editor i botten av filen
 */
object main {
  val dateFormat = new SimpleDateFormat("yyy-MM-dd")
  
  def help(){
    println("noob")
  }
  
  def p(e : Entry){
    println(dateFormat.format(e.date) + "\n" + e.text)
  }
  
  def p(bok: Dagbok, date: Date){
    println(dateFormat.format(date))
    bok.getByDate(date) match {
      case Some(entry) => p(entry);
      case None => println("No entry")
    }
  }
	
  def main(args: Array[String]) = {
    args.foreach(f => 
     f match {
      case "-h" | "--help" => help()
      case _ =>
    })
    
//    val dagbok = Dagbok(args(args.length -1))
    val d = Dagbok("sdg.txt")
//    println(d.name + " contains " + d.entries.length + " entries")
//    d.entries.foreach(e => p(e))
    
    val day = Calendar.getInstance()
    day.setTime(new Date())
    
    println("Todays entry:")
    p(d, day.getTime())
    
    println("Yesterdays entry:")
    day.add(Calendar.DATE, -1)
    p(d, day.getTime())
    
    println("Previous weeks entry:")
    day.add(Calendar.DATE, -6)
    p(d, day.getTime())
    
    println("Previus month entry:")
    day.add(Calendar.DATE, -23)
    p(d, day.getTime())
  }
}