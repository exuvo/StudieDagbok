package se.exuvo
import java.text.DateFormat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class Entry(val date: Date, val text: String) {
}

class Dagbok(val name: String, val entries: Array[Entry]) {
  
  def getByDate(date: Date) : Option[Entry] = {
    val en = entries.filter(e => e.date.getYear() == date.getYear() && e.date.getMonth() == date.getMonth() && e.date.getDate() == date.getDate())
    if(en.length > 0) Option(en(0))
    else Option(null)
  }
}

object Dagbok{
  def apply(filename: String): Dagbok = {
	  val source = scala.io.Source.fromFile(filename)
	   
	  val datePattern = "([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})".r
	  val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
	  var date: Date = null
	  var text: StringBuilder = new StringBuilder(512);
	  var entries: Array[Entry] = Array[Entry]()
		  
	  val lines = source.getLines().foreach(l => l match{
	    case datePattern(d) => {
	      if(text.length > 0) {
	        entries = entries :+ new Entry(date, text.toString())
	        text.clear()
	      }
	    	date = dateFormat.parse(d)
	    }
	    case t if(!t.isEmpty() && !t.matches("[ ]+")) => text.append(t + "\n")
	    case _ =>
	  })
	  if(text.length > 0) {
        entries = entries :+ new Entry(date, text.toString())
    }
	  source.close()
	  
	  new Dagbok(filename, entries)
	}
}