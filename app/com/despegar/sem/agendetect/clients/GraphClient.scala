package com.despegar.sem.agendetect.clients

import scala.util.Try
import scala.util.control.NonFatal
import scalaj.http.Http
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats
import play.api.Logger

case class ErdosRelationInfo(`type`:String, deviceType:String)
case class ErdosRelation(`type`:String, id:String, info:Option[ErdosRelationInfo])
case class ErdosResponse(id:String, relationships:Seq[ErdosRelation])

class GraphClient(configuration: play.api.Configuration) {

  def getRelationsIds(id:String): Seq[String] = {

    val url = buildURL(id)

    Logger.info("GraphClient is connecting to URL:" + url)

    val responseTry = Try(Http(url).asString)
    responseTry.map{ response =>
      implicit val formats = DefaultFormats
      val container: Option[ErdosResponse] = parse(response.body).extractOpt[ErdosResponse]
      container match {
        case Some(c) => id +: c.relationships.map(_.id)
        case None => Seq(id)
      }
    }.recover{case NonFatal(e) =>
      Logger.info(s"Request to id: $id failed. Cause: $e")
      Seq(id)
    }.get
  }


  private def buildURL(userId:String): String =
  {
    val url = configuration.get[String]("graph.client.url")
    url.format(userId)
  }

}
