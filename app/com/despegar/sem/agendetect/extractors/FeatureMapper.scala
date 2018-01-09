package com.despegar.sem.agendetect.extractors

import java.sql.Timestamp
import java.time.LocalDateTime
import com.despegar.kairos.commons.models.UserEvent
import com.despegar.sem.agendetect.persistence.entity.UserFeature
import com.despegar.sem.agendetect.utils.Constants._
import com.despegar.sem.agendetect.utils.MathUtils.variance
import com.despegar.sem.agendetect.utils.TimeUtils.getTimeZone
import com.despegar.sem.agendetect.utils.ParseUtils.{getField, isFlow, isProduct, isRoute}

object FeatureMapper {


   def mapFeature(userEvents:Seq[UserEvent]):UserFeature=
  {


    val result= userEvents.map(x=>generateFeature(x)).groupBy(_=>true). map {
      case (_, trans) =>


        UserFeature(trans.map(_.combinedProducts).sum, trans.map(_.cars).sum, trans.map(_.hotels).sum, trans.map(_.flights).sum,
          trans.map(_.home).sum,trans.map(_.search).sum,trans.map(_.detail).sum,trans.map(_.checkout).sum,trans.map(_.landing).sum,trans.map(_.thanks).sum,
          trans.map(_.d1).sum,trans.map(_.d2).sum,trans.map(_.d3).sum,trans.map(_.d4).sum,trans.map(_.d5).sum,trans.map(_.d6).sum,trans.map(_.d7).sum,
          trans.map(_.h1).sum,trans.map(_.h2).sum,trans.map(_.h3).sum,trans.map(_.h4).sum,trans.map(_.h5).sum,trans.map(_.h6).sum,trans.map(_.h7).sum,trans.map(_.h8).sum,trans.map(_.h9).sum,trans.map(_.h10).sum,trans.map(_.h11).sum,trans.map(_.h12).sum,
          trans.map(_.s1).sum,trans.map(_.s2).sum,trans.map(_.s3).sum,trans.map(_.s4).sum,
          trans.map(_.home).sum+trans.map(_.search).sum+trans.map(_.detail).sum+trans.map(_.checkout).sum+trans.map(_.landing).sum+trans.map(_.thanks).sum,
          trans.map(_.dayOfYear).distinct.length,
          variance(trans.map(_.dayOfYear)),variance(trans.map(_.dayOfWeek)),variance(trans.map(_.hourOfDay)),
          trans.map(_.ip).distinct.length,trans.map(_.cc).distinct.length,trans.map(_.international).sum,trans.map(_.domestic).sum)
    }


     result.last
  }


  private def generateFeature(userEvent: UserEvent):UserFeatureRecord=
  {

    var hours:Array[Int]= new Array[Int](12)
    var seasons:Array[Int]= new Array[Int](4)
    var days:Array[Int]= new Array[Int](7)
    var ip= getField(userEvent.actionData,IP_FIELD_TAG)
    var cc= getField(userEvent.actionData,COUNTRY_FIELD_TAG)
    var ro= getField(userEvent.actionData,ROUTE_FIELD_TAG)
    val timeZone = getTimeZone(cc)
    val timestamp = new Timestamp(userEvent.datetime)
    val localTime = LocalDateTime.ofInstant(timestamp.toInstant,timeZone.toZoneId)
    val month=localTime.getMonth.getValue
    val hourOfDay=localTime.getHour
    val dayOfWeek=localTime.getDayOfWeek.getValue-1
    val dayOfyear=localTime.getDayOfYear
    hours(hourOfDay/2)=1
    seasons(month/4)=1
    days(dayOfWeek)=1


    UserFeatureRecord(userEvent.userId,
      isProduct (userEvent.product.toUpperCase,PRODUCT_COMBINED_PRODUCTS),
      isProduct (userEvent.product.toUpperCase,PRODUCT_CARS),
      isProduct (userEvent.product.toUpperCase,PRODUCT_HOTELS),
      isProduct (userEvent.product.toUpperCase,PRODUCT_FLIGHTS),
      isFlow(userEvent.flow.toUpperCase ,FLOW_HOME),
      isFlow (userEvent.flow.toUpperCase ,FLOW_SEARCH),
      isFlow (userEvent.flow.toUpperCase ,FLOW_DETAIL),
      isFlow (userEvent.flow.toUpperCase ,FLOW_CHECKOUT),
      isFlow (userEvent.flow.toUpperCase ,FLOW_LANDING),
      isFlow (userEvent.flow.toUpperCase ,FLOW_THANKS),
      days(0),days(1),days(2),days(3),days(4),days(5),days(6),
      hours(0),hours(1),hours(2),hours(3),hours(4),hours(5),hours(6),hours(7),hours(8),hours(9),hours(10),hours(11),
      seasons(0),seasons(1),seasons(2),seasons(3),
      dayOfyear, dayOfWeek, hourOfDay, ip, cc,
      isRoute(ro.toUpperCase , ROUTE_INTERNATIONAL),
      isRoute(ro.toUpperCase , ROUTE_DOMESTIC)

    )


  }

   case class UserFeatureRecord(userId: String, combinedProducts: Int, cars: Int, hotels: Int, flights: Int, home: Int, search: Int, detail: Int, checkout:Int, landing:Int, thanks:Int, d1:Int, d2:Int, d3:Int, d4:Int, d5:Int, d6:Int, d7:Int, h1:Int, h2:Int, h3:Int, h4:Int, h5:Int, h6:Int, h7:Int, h8:Int, h9:Int, h10:Int, h11:Int, h12:Int, s1:Int, s2:Int, s3:Int, s4:Int, dayOfYear:Int, dayOfWeek:Int,hourOfDay:Int, ip:String,cc:String,international:Int,domestic:Int)


}
