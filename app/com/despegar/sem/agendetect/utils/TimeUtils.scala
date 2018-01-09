package com.despegar.sem.agendetect.utils

import java.util.TimeZone

object TimeUtils {


  def getTimeZone(cc:String):TimeZone=
  {


    var timeZoneString="America/Argentina/Buenos_Aires"

    cc match {

      case "AR"  => TimeZone.getTimeZone("America/Argentina/Buenos_Aires")
      case "BR"  => TimeZone.getTimeZone("America/Sao_Paulo")
      case "MX"  => TimeZone.getTimeZone("America/Mexico_City")
      case "CO"  => TimeZone.getTimeZone("America/Bogota")
      case "PE"  => TimeZone.getTimeZone("America/Lima")
      case "US"  => TimeZone.getTimeZone("America/Chicago")
      case "VE"  => TimeZone.getTimeZone("America/Caracas")
      case "UY"  => TimeZone.getTimeZone("America/Montevideo")
      case "PR"  => TimeZone.getTimeZone("America/Puerto_Rico")
      case "PA"  => TimeZone.getTimeZone("America/Panama")
      case "GB"  => TimeZone.getTimeZone("Europe/London")
      case "GE"  => TimeZone.getTimeZone("Asia/Tbilisi")
      case "EC"  => TimeZone.getTimeZone("America/Guayaquil")
      case "DO"  => TimeZone.getTimeZone("America/Santo_Domingo")
      case "CR"  => TimeZone.getTimeZone("America/Costa_Rica")
      case "CL"  => TimeZone.getTimeZone("America/Santiago")
      case whoa  => TimeZone.getTimeZone("America/Argentina/Buenos_Aires")

    }

  }

}
