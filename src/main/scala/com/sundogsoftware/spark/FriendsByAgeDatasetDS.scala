package com.sundogsoftware.spark

import com.sundogsoftware.spark.FriendsByAgeDataset.FakeFriends
import org.apache.spark.sql._
import org.apache.log4j._

object FriendsByAgeDatasetDS {
 case class Friends(id: Int, name: String, age: Int, friends: Long)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder
      .appName("FriendsByAge")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val friends = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Friends]

    val nameAge = friends.select("name","friends")

    val groupByAge = nameAge.groupBy("age").avg("friends")

  }
}
