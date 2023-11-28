import MovieRatingAnalyzer.analyzer
import org.apache.spark.sql.SparkSession
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

import java.beans.Transient

class MovieRatingsAnalyzerTest extends AnyFunSuite with BeforeAndAfterAll {

  @Transient var spark: SparkSession = _

  override def beforeAll(): Unit = {
    // Create SparkSession only if it doesn't exist
    if (spark == null) {
      spark = SparkSession.builder()
        .appName("MovieRatingAnalyzerTest")
        .master("local[*]")
        .getOrCreate()
    }
  }

  override def afterAll(): Unit = {
    // Stop SparkSession if it was created
    if (spark != null) {
      spark.stop()
    }
  }

  test("Analyzing movie rating") {
    val result = analyzer(spark)
    assert(result.count() == 45115, "result count should be")
  }
}