package com.despegar.sem.agendetect.utils

import java.io.{FileInputStream, FileOutputStream}
import com.twitter.chill.{Input, KryoBase, Output, ScalaKryoInstantiator}
import org.apache.spark.mllib.clustering.KMeansModel


trait ModelPersist
{

  def save(model: KMeansModel, folderName:String, filename:String):Boolean
  def load(folderName: String, filename: String):KMeansModel

}


class KryoModelPersist  extends  ModelPersist {


  val kryo: KryoBase = {
    val instantiator = new ScalaKryoInstantiator
    instantiator.setRegistrationRequired(false)
    instantiator.newKryo
  }

  override def save(model: KMeansModel, folderName: String, filename: String):Boolean =
  {

    try {

        val outputFile = new Output(new FileOutputStream(folderName+filename))
        kryo.writeObject(outputFile, model)
        outputFile.close()
        return true

    }
      catch {
        case e: Exception => throw e

      }

  }

  override def load(folderName: String, filename: String):KMeansModel =
  {

    try{
      val inputFile = new Input(new FileInputStream(folderName+filename))
      val deserPred = kryo.readObject(inputFile, classOf[KMeansModel])
      deserPred

    }
    catch {
      case e: Exception => throw e

    }

  }

}
