package com.despegar.sem.agendetect.services

import com.despegar.kairos.commons.models.UserEvent
import com.despegar.sem.agendetect.clients.GraphClient
import com.despegar.sem.agendetect.extractors.FeatureMapper
import com.despegar.sem.agendetect.model.Model
import com.despegar.sem.agendetect.persistence.AgenCache
import com.despegar.sem.agendetect.persistence.dao.UserEventsDAO
import com.despegar.sem.agendetect.utils.ResultType
import play.api.Logger
import com.despegar.sem.agendetect.utils.ResultType._
import com.despegar.sem.agendetect.utils.Constants.EMPTY_EVENTS



trait Service
{
  def execute(userId:String):Response
}


class AgenDetectService(model : Model, graphClient:GraphClient, userEventsDAO:UserEventsDAO, agentCache : AgenCache) extends Service {



  def execute(userId:String):Response = {

      Logger.info(s"[REQUEST RECEIVED] userId:$userId")


    if (agentCache.get(userId)) {

      Logger.info(s"[ID AGENCY CACHE FOUND]:$userId")
      Response(ResultType.AGENCY,EMPTY_EVENTS)

    }else{


      val eventsFull = userEventsDAO.getUserEvents(userId)

      eventsFull.size  match {

        case 0 => {

          Response(ResultType.EMPTY,EMPTY_EVENTS)}

        case _ => {


          model.predict(FeatureMapper.mapFeature(eventsFull).toVector) match
          {

            case true => {

              val relations = graphClient.getRelationsIds(userId)
              relations.foreach(uid => agentCache.put(uid))
              Response(ResultType.AGENCY,EMPTY_EVENTS)

            }
            case false => {

              Response(ResultType.USER,eventsFull)

            }

          }

        }
      }

    }



  }

}

case class Response(resultType: ResultType, events: Seq[UserEvent])

