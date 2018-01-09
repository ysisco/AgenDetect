package com.despegar.sem.agendetect.persistence.dao

import com.despegar.kairos.commons.models.UserEvent
import com.despegar.sem.agendetect.utils.Constants
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import scala.concurrent.Await
import scala.concurrent.duration.Duration


/**
  * Created by ysisco on 15/12/17.
  */

class UserEventsDAO(dbConfig: DatabaseConfig[JdbcProfile],configuration: play.api.Configuration){

  import dbConfig.profile.api._

  private val db = dbConfig.db
  private val userEvents = TableQuery[UserEvents]


  def exec[T](action: DBIO[T]): T =
  {
    Await.result(db.run(action), Duration.Inf)
  }

  private  def getUserData(userID: slick.lifted.Rep[String]) = {

    userEvents.filter(_.userId===userID)
              .filter(_.product.toUpperCase inSet(Traversable(Constants.PRODUCT_HOTELS,Constants.PRODUCT_CARS,Constants.PRODUCT_COMBINED_PRODUCTS,Constants.PRODUCT_FLIGHTS)))
              .filter(_.flow.toUpperCase inSet(Traversable(Constants.FLOW_LANDING,Constants.FLOW_HOME,Constants.FLOW_SEARCH,Constants.FLOW_DETAIL,Constants.FLOW_CHECKOUT,Constants.FLOW_THANKS)))
              .take(configuration.get[Int]("query.max.limit"))

  }


  def getUserEvents(userID:String): Seq[UserEvent]=
  {

    var userQuery = getUserData(userID)
    val action = userQuery.result
    exec(action)
  }



  private class UserEvents(tag: Tag) extends Table[UserEvent](tag, "user_events"){
    def userId = column[String]("user_id", O.PrimaryKey)
    def datetime = column[Long]("datetime", O.PrimaryKey)
    def brand = column[String]("brand")
    def flow = column[String]("flow")
    def product = column[String]("product")
    def countryCode = column[String]("country_code")
    def actionData = column[String]("action_data")
    def * = (userId,datetime,brand,flow,product,countryCode,actionData) <> (UserEvent.tupled, UserEvent.unapply)
  }


}
