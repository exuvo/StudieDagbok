package se.exuvo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import java.io.File

/**
 * Program för att läsa tidigare dagar och repetera dom
 * Ska endast läsa text fil och automatisk känna av av dag som en viss rad är inskriven efter datum rad
 * Användaren får skriva in ny dag med valfri text editor i botten av filen
 */
object main {
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
  
  def help(){
    println("[filename]")
  }
  
  def p(bok: Dagbok, date: Date){
    println(dateFormat.format(date))
    bok.getByDate(date) match {
      case Some(entry) => print(entry.text);
      case None => println("No entry")
    }
  }
	
  def main(args: Array[String]) = {
    var filename = "sdg.txt"
      
    args.foreach(f => 
     f match {
      case "-h" | "--help" => help(); exit()
      case f => if(new File(f).exists()) filename = f
    })
    
    val d = Dagbok(filename)
    println(d.name + " contains " + d.entries.length + " entries")
//	  d.entries.foreach(e => println(dateFormat.format(e.date)) + "\n" + e.text)
    
    val day = Calendar.getInstance()
    day.setTime(new Date())
    
    println("\nTodays entry:")
    p(d, day.getTime())
    
    println("\nYesterdays entry:")
    day.add(Calendar.DATE, -1)
    p(d, day.getTime())
    
    println("\nPrevious week entry:")
    day.add(Calendar.DATE, -6)
    p(d, day.getTime())
    
    println("\nPrevius month entry:")
    day.add(Calendar.DATE, -23)
    p(d, day.getTime())
  }
}