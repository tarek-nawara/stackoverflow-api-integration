package edu.stackoverflow.engine

import java.util.Date

import scala.collection.JavaConverters
import edu.stackoverflow.data.{Answer, AnswersContainer, Owner}
import org.apache.spark.{SparkConf, SparkContext}

/** The Engine is responsible for all the analysis happening
  * on the data.
  *
  * @author tarek-nawara
  * @version 1.0
  */
class Engine(answersContainer: AnswersContainer) {
  private val conf = new SparkConf().setAppName("stackoverflow").setMaster("local")
  private val sc = new SparkContext(conf)
  private val answersRDD = sc.makeRDD(JavaConverters.asScalaBuffer(answersContainer.getAnswers))

  /** Rank the owners according to the total number of answers' score
    * they have in descending order.
    *
    * @return sorted Seq of the ranked owners
    */
  def rankOwnersWithScore(): Seq[(Owner, Int)] = {
    val result = answersRDD.groupBy(_.getOwner).mapValues(_.map(_.getScore).sum).collect()
    result.sortBy(_._2)
  }

  /** Rank the accepted answers according to its `score`.
    *
    * @return list of ordered accepted answers
    */
  def rankAnswersWithScore(): Seq[Answer] = {
    answersRDD.filter(_.isAccepted).sortBy(_.getScore).collect()
  }

  /** Get all the answers created after the given date.
    *
    * @param startDate target date
    * @return all answers created after the target date
    */
  def findAnswersAfterCreationDate(startDate: Date): Seq[Answer] = {
    answersRDD.filter(_.getCreationDate.compareTo(startDate) >= 0).collect()
  }

  /** Group all the answers with the corresponding owners.
    *
    * @return group of answers with owners as the `key`
    */
  def groupByOwners(): Array[(Owner, Iterable[Answer])] = {
    answersRDD.groupBy(_.getOwner).collect()
  }
}
