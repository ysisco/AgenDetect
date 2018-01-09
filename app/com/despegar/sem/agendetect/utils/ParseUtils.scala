package com.despegar.sem.agendetect.utils

import com.despegar.kairos.commons.models.UserEvent
import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import play.api.mvc.{AnyContent, Request}
import play.api.Logger

object ParseUtils {



  def isProduct(product:String,productType:String):Int = if(product==productType) 1 else 0
  def isFlow(flow:String,flowType:String):Int = if(flow==flowType) 1 else 0
  def isRoute(route:String,routeType:String):Int = if(route.replace("\"","")==routeType) 1 else 0



  def parse(line: String) = {
    val pieces = line.split(',')
    val userid = pieces(0)
    val dn = pieces(1)
    val event = pieces(2)
    val email = pieces(3)
    AgencyDataRecord(userid, dn, event, email)
  }



  def getField(request: Request[AnyContent], field:String):String =
  {

    var fieldValue = ""

    println("request=" + request.body)

    val jsonBody = request.body.asJson.get
    val jsonMessage = Json.toJson(jsonBody)



    fieldValue = ((jsonMessage \ field).get).toString

    fieldValue.replace("\"", "")
  }


  def getField(request: String, field:String):String =
  {

    var fieldValue = ""
    val json: JsValue = Json.parse(request)

    try {

      fieldValue = ((json \ field).get).toString

    } catch {

      case _: Throwable => //Logger.debug( field +" not found in Json dataAction...")
    }

    fieldValue
  }


  def seqToJSon(seq:Seq[UserEvent]):String
  ={"["+ seq.mkString("{", ",", "}")+"]"}


  case class AgencyDataRecord(userid: String, dn: String, event: String, email: String)

}
