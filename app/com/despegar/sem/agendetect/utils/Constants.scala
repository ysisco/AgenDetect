package com.despegar.sem.agendetect.utils

import java.util.TimeZone

import com.despegar.kairos.commons.models.UserEvent

object Constants {
  val FLOW_IP_TAG = "ip"
  val FLOW_FIELD_TAG = "fl"
  val PRODUCT_FIELD_TAG = "pr"
  val DOMAIN_FIELD_TAG = "dn"
  val COUNTRY_FIELD_TAG = "cc"
  val DESTINATION_FIELD_TAG = "dc"
  val DATETIME_FIELD_TAG = "datetime"
  val USERID_FIELD_TAG = "userid"
  val IP_FIELD_TAG = "ip"
  val ROUTE_FIELD_TAG = "ro"

  val PRODUCT_CARS = "CARS"
  val PRODUCT_FLIGHTS = "FLIGHTS"
  val PRODUCT_HOTELS = "HOTELS"
  val PRODUCT_COMBINED_PRODUCTS = "COMBINEDPRODUCTS"


  val FLOW_HOME= "HOME"
  val FLOW_LANDING= "LANDING"
  val FLOW_DETAIL= "DETAIL"
  val FLOW_CHECKOUT= "CHECKOUT"
  val FLOW_THANKS= "THANKS"
  val FLOW_SEARCH= "SEARCH"


  val ROUTE_INTERNATIONAL= "INTERNATIONAL"
  val ROUTE_DOMESTIC= "DOMESTIC"

  val AR: TimeZone = TimeZone.getTimeZone("America/Argentina/Buenos_Aires")
  val BR: TimeZone = TimeZone.getTimeZone("America/Sao_Paulo")
  val MX: TimeZone = TimeZone.getTimeZone("America/Mexico_City")
  val CO: TimeZone = TimeZone.getTimeZone("America/Bogota")
  val PE: TimeZone = TimeZone.getTimeZone("America/Lima")
  val US: TimeZone = TimeZone.getTimeZone("America/Chicago")
  val VE: TimeZone = TimeZone.getTimeZone("America/Caracas")
  val UY: TimeZone = TimeZone.getTimeZone("America/Montevideo")
  val PR: TimeZone = TimeZone.getTimeZone("America/Puerto_Rico")
  val PA: TimeZone = TimeZone.getTimeZone("America/Panama")
  val GB: TimeZone = TimeZone.getTimeZone("Europe/London")
  val GE: TimeZone = TimeZone.getTimeZone("Asia/Tbilisi")
  val EC: TimeZone = TimeZone.getTimeZone("America/Guayaquil")
  val DO: TimeZone = TimeZone.getTimeZone("America/Santo_Domingo")
  val CR: TimeZone = TimeZone.getTimeZone("America/Costa_Rica")
  val CL: TimeZone = TimeZone.getTimeZone("America/Santiago")


  var HOUR : Int = 0
  var DAY :  Int = 1
  var MONTH: Int = 2
  var YEAR:  Int = 3
  var DAY_OF_WEEK :  Int = 4
  var DAY_OF_YEAR :  Int = 5


  var MODEL_RESULT_USER=0
  var MODEL_RESULT_AGENCY=0
  var MODEL_RESULT_EMPTY=0

  val EMPTY_EVENTS = Seq[UserEvent]()

}
