package se.exuvo

class Entry {
  val date
  val text: String
}

class Dagbok(fil) {
	val file
	val entries: Array[Entry]
	
}