import org.apache.spark.sql.{DataFrame, SparkSession, functions}


object MovieRatingAnalyzer extends App{

  def analyzer(spark: SparkSession): DataFrame = {
    val path = "movie-rating/src/main/resources/ratings.csv"

    val df = spark.read
      .option("header", true)
      .option("inferSchema", true)
      .csv(path)

    df.printSchema()
    println("Origin row count:" + df.count())
    df.show(10)

    val result = df.groupBy("movieId").agg(
      functions.mean("rating").alias("Mean rating"),
      functions.stddev("rating").alias("Standard Deviation")
    )
    result.show(10)

    println("total movie count:" + result.count())
    result
  }

  val spark: SparkSession = SparkSession
    .builder()
    .appName("MovieRatingAnalyzer")
    .master("local[*]")
    .getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

  analyzer(spark)
}
