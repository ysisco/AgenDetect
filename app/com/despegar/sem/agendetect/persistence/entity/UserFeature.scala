package com.despegar.sem.agendetect.persistence.entity

import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors

case class UserFeature(totalCombinedProducts: Double, totalCars: Double, totalHotels: Double,  totalFlights: Double,totalHome: Double,
                       totalSearch: Double, totalDetail: Double, totalCheckout:Double, totalLanding:Double,totalThanks:Double, d1:Double,
                       d2:Double, d3:Double, d4:Double, d5:Double, d6:Double, d7:Double, h1:Double, h2:Double, h3:Double, h4:Double, h5:Double,
                       h6:Double, h7:Double, h8:Double, h9:Double, h10:Double, h11:Double, h12:Double, s1:Double, s2:Double, s3:Double, s4:Double,
                       total:Double,  activityDays:Double,dayOfYearDev:Double,dayOfWeekDev:Double,hourOfDayDev:Double, ipCount:Double,
                       ccCount:Double , totalInternational:Double,totalDomestic:Double)
{


   def toVector():linalg.Vector =
  {

    val r:Array[Double]=new Array[Double](43)



    val aClass = UserFeature.getClass
    val fields = aClass.getDeclaredFields
    var i =0


    r(0)=this.totalHome
    r(1)=this.totalSearch
    r(2)=this.totalDetail
    r(3)=this.totalCheckout
    r(4)=this.totalLanding
    r(5)=this.totalThanks
    r(6)=this.totalFlights
    r(7)=this.totalCombinedProducts
    r(8)=this.totalHotels
    r(9)=this.totalCars
    r(10)=this.h1
    r(11)=this.h2
    r(12)=this.h3
    r(13)=this.h4
    r(14)=this.h5
    r(15)=this.h6
    r(16)=this.h7
    r(17)=this.h8
    r(18)=this.h9
    r(19)=this.h10
    r(20)=this.h11
    r(21)=this.h12
    r(22)=this.d1
    r(23)=this.d2
    r(24)=this.d3
    r(25)=this.d4
    r(26)=this.d5
    r(27)=this.d6
    r(28)=this.d7
    r(29)=this.s1
    r(30)=this.s2
    r(31)=this.s3
    r(32)=this.s4
    r(33)=this.total
    r(34)=this.activityDays
    r(35)=this.dayOfYearDev
    r(36)=this.dayOfWeekDev
    r(37)=this.hourOfDayDev
    r(38)=this.ipCount
    r(39)=this.ccCount
    r(40)=this.totalDomestic
    r(41)=this.totalInternational
    r(42)=1.0

    Vectors.dense(r)

  }

}
