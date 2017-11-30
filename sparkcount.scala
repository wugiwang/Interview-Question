// Interview Question

import java.io.FileWriter
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object InterviewObj {
   def main(args: Array[String]) {
       val inputDir = args(0)
       val outputDir = args(1)

       val conf = new SparkConf().setAppName("interview").setMaster("local[2]")
       val sc = new SparkContext(conf) // Load context into spark

       val rdd: RDD[String] = sc.textFile(inputDir)

       val wc = rdd.flatMap { str =>
           str.toLowerCase.dropRight(1).split(" ").map { w =>
               (w, 1)
           }
       }.reduceByKey(_ + _)

       val fw = new FileWriter(outputDir)
       wc.collect.map { case (k, v) =>
           fw.append(k + ":" + v + "\n")
       }
       fw.close
   }
}