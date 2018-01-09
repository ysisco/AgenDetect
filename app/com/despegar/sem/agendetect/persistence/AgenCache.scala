package com.despegar.sem.agendetect.persistence

import org.mapdb.{DBMaker, Serializer}

class AgenCache(configuration: play.api.Configuration) {


   val db = DBMaker.fileDB(configuration.get[String]("cache.file.path"))
     .fileMmapEnable()
     .closeOnJvmShutdown().transactionEnable()
     .make();


  val map = db.hashMap("map", Serializer.STRING, Serializer.LONG).createOrOpen()

  def close():Unit= db.close()

  def get(key:String):Boolean = map.containsKey(key)

  def put(key:String):Unit =
   { map.put(key,1L)
     db.commit()
   }

}
