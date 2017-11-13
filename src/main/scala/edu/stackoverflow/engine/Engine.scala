package edu.stackoverflow.engine

import scala.collection.JavaConverters
import edu.stackoverflow.data.{AnswersContainer, Owner}
import org.apache.spark.{SparkConf, SparkContext}

/** The Engine is responsible for all the analysis happening
  * on the data.
  *
  * @author tarek-nawara
  * @version 1.0
  */
class Engine {
  private val conf = new SparkConf().setAppName("stackoverflow").setMaster("local")
  private val sc = new SparkContext(conf)

  /** Rank the owners according to the total number of answers' score
    * they have in descending order.
    *
    * @param answersContainer `stackoverflow` API response
    * @return sorted Seq of the ranked owners
    */
  def rankOwnersWithScore(answersContainer: AnswersContainer): Seq[(Owner, Int)] = {
    val answers = JavaConverters.asScalaBuffer(answersContainer.getAnswers)
    val answersRDD = sc.makeRDD(answers)
    val result = answersRDD.groupBy(_.getOwner).mapValues(_.map(_.getScore).sum).collect()
    result.sortBy(_._2)
  }
}
