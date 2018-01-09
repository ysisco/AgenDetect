package com.despegar.sem.agendetect.loader


import com.despegar.sem.agendetect.clients.GraphClient
import com.despegar.sem.agendetect.controller.AgenDetectController
import com.despegar.sem.agendetect.persistence.AgenCache
import com.despegar.sem.agendetect.persistence.dao.UserEventsDAO
import com.despegar.sem.agendetect.model.AgenDetectModel
import com.despegar.sem.agendetect.services.AgenDetectService
import com.despegar.sem.agendetect.utils.KryoModelPersist
import play.api.ApplicationLoader.Context
import com.softwaremill.macwire._
import play.api.mvc.EssentialFilter
import play.filters.csrf.CSRFFilter
import play.api._
import play.api.db.slick.{DbName, SlickComponents}
import router.Routes
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile




class  ConfigLoader extends ApplicationLoader with  Serializable {

  implicit val ex = scala.concurrent.ExecutionContext.Implicits.global


  def load(context: Context) = {

    new Components(context).application
  }
}


class Components(context: Context)
  extends BuiltInComponentsFromContext(context)
    with play.filters.HttpFiltersComponents with SlickComponents {

  Logger.info("Starting App...")

  protected lazy val dbConfig: DatabaseConfig[JdbcProfile] = this.slickApi.dbConfig[JdbcProfile](DbName("semAgency"))
  protected lazy val agentCache : AgenCache =wire[AgenCache]
  protected lazy val userEventsDAO : UserEventsDAO =wire[UserEventsDAO]
  protected lazy val agenCheckModel : AgenDetectModel =wire[AgenDetectModel]
  protected lazy val kryoModelPersist: KryoModelPersist = wire[KryoModelPersist]
  protected lazy val mainController: AgenDetectController = wire[AgenDetectController]
  protected lazy val agenCheckService: AgenDetectService = wire[AgenDetectService]
  protected lazy val graphClient:GraphClient = wire[GraphClient]

  lazy val prefix: String = "/"
  lazy val router = wire[Routes]

  override def httpFilters: Seq[EssentialFilter] = {
    super.httpFilters.filterNot(_.getClass == classOf[CSRFFilter])
  }





}