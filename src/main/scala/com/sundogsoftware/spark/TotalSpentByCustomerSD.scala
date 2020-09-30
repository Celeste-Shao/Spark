package com.sundogsoftware.spark

//导入packages
import org.apache.spark._
import org.apache.log4j._

//application的object
object TotalSpentByCustomerSD {
  //定义data schema的function
  def extractCustomerWithSchema(line: String): (Int, Float) = {
  val fields = line.split(",")
    (fields(0).toInt, fields(2).toFloat)
  }
  //定义main function
  def main(args: Array[String]): Unit = {
    //选取需要的log（全都print，太多了，只需要有error的时候print）
    Logger.getLogger("org").setLevel(Level.ERROR)
    //构建新的SparkContext
    val sc = new SparkContext("local[*]", "TotalSpentByCustomer")
    //input路径&读取文件
    val input = sc.textFile("data/customer-orders.csv")
    //用定义好的schema扫描文件
    val mappedInput = input.map(extractCustomerWithSchema)
    //reduceByKey
    val TotalByCustomer = mappedInput.reduceByKey( (x,y) => x + y)
    //collect结果
    val result = TotalByCustomer.collect()
    //print结果
    result.foreach(println)
  }

}
