package query.controllers

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import javax.inject._
import play.api.mvc._
import play.api.libs.json.{JsObject, Json}

import query.models._
import query.services.QueryService

@Singleton
class QueryController @Inject() (cc: ControllerComponents, queryService: QueryService)(implicit
  ec: ExecutionContext
) extends AbstractController(cc) {

  def getPost(id: Long): Action[AnyContent] = Action.async { _ =>
    val postsJson = queryService.getPostsJson(id)
    Future.successful(Ok(Json.toJson(ApiResult(result = Some(postsJson)))))
  }

  def getPosts: Action[AnyContent] = Action.async { _ =>
    val postIds = queryService.getPostsIds()
    val postsJson = postIds.map(id => queryService.getPostsJson(id))
    Future.successful(Ok(Json.toJson(ApiResult(result = Some(Json.toJson(postsJson))))))
  }
}
