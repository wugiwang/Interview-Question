// Interview Question

import scala.io.Source._
import java.io.FileWriter

object InterviewObj {
   def main(args: Array[String]) {
       val inputDir = args(0)
       val outputDir = args(1)

       val wc = fromFile(inputDir).getLines.flatMap { str =>
           str.toLowerCase.dropRight(1).split(" ")
       }.toArray.groupBy(identity).map(kv => (kv._1, kv._2.size))

       val fw = new FileWriter(outputDir)
       wc.map { case (k, v) =>
           fw.append(k + ":" + v + "\n")
       }
       fw.close
   }
}