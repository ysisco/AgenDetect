package com.despegar.sem.agendetect.model

import com.despegar.sem.agendetect.utils.ModelPersist
import org.apache.spark.mllib.clustering._
import org.apache.spark.mllib.linalg._
import play.api.Logger



trait  Model {

  def setup():Unit
  def train():KMeansModel
  def load():KMeansModel
  def predict(data:Vector):  Boolean
  def save(model: KMeansModel)

}

class AgenDetectModel(configuration: play.api.Configuration, modelPersist: ModelPersist) extends Model with  Serializable {



  private val kmeans = new KMeans()
  private val model=load()


  override def setup():Unit =
  {

    Logger.info("Method not implemented... ")
  }


  override def train():KMeansModel=

  {

    Logger.info("Method not implemented... ")
    null
  }


  override def load():KMeansModel=

  {
    Logger.info("Loading model... ")
    modelPersist.load(configuration.get[String]("data.model.directory"),configuration.get[String]("data.model.name"))

  }


  override def predict(data:Vector):  Boolean =
  {

    Logger.info("Predict vector :  " + data)
    var result= model.predict(data)
    Logger.info("Result :  " + result)
    (result>0)
  }


  override def save(model: KMeansModel): Unit =
  {
    modelPersist.save(model,configuration.get[String]("data.model.directory"),configuration.get[String]("data.model.name"))
  }
}