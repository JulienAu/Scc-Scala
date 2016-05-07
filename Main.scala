import java.io.PrintWriter
import java.io.FileWriter
import java.io.File
import java.io.BufferedWriter
import java.io.IOException


object Main {

	def main(args: Array[String]): Unit = {
			val n = 100;
			args.foreach(arg => getTime(arg))
	}

	def getTime(arg:String){
		val tarjan = new Tarjan(buildExampleGraph(arg))
		val time1= System.nanoTime();
		tarjan.Cfgs()
		val time2= System.nanoTime();
		/*
		withPrintWriter(".", "myFile") { printWriter =>
		printWriter.write((time2-time1).toString)
		}*/

		val print:PrintWriter = new PrintWriter(new BufferedWriter(new FileWriter("myFile", true)));
		print.println((time2-time1).toString);
		//more code

		//exception handling left as an exercise for the reader

	}

	def buildExampleGraph (graphStr:String): Graph[String] = {
			val graph = new Graph[String]()

					graph.addSommets(graphStr)

					graph
	}

	def withPrintWriter(dir:String, name:String)(f: (PrintWriter) => Any) {
		val file : File = new File("myFile")
	val writer = new FileWriter(file )
	val printWriter = new PrintWriter(writer)
	try {
		f(printWriter)
	}
	finally {
		printWriter.close()
	}
	}
}