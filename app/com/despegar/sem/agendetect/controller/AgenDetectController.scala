package com.despegar.sem.agendetect.controller

import javax.inject.{Inject, Singleton}
import com.despegar.sem.agendetect.services.Service
import com.despegar.sem.agendetect.utils.Constants.USERID_FIELD_TAG
import com.despegar.sem.agendetect.utils.ParseUtils._
import com.despegar.sem.agendetect.utils.ResultType
import play.api.mvc._
import play.api.Logger


@Singleton
class AgenDetectController @Inject()(service:Service,cc: ControllerComponents) extends AbstractController(cc) {

  def execute() = Action { implicit request: Request[AnyContent] =>



    val userId = getField(request,USERID_FIELD_TAG)
    val result = service.execute(userId)
    val events = seqToJSon(result.events)

//    Logger(s"[SERVICE RESULT TO $userId]:$result")

    result.resultType match
    {
      case ResultType.USER =>  Ok(events)
      case ResultType.AGENCY => NotFound(events)
      case ResultType.EMPTY => NoContent
    }

  }

}
