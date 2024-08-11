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
    val post = queryService.getPost(id)
    val comments = queryService.getPostComments(id)
    post match
      case Some(p) =>
        val commentsWithReactions = comments.map { c =>
          val reactions = queryService.getCommentReactions(c.id)
          val reactionsJson = Json.toJson(reactions)
          Json.toJson(c).as[JsObject] + ("reactions" -> reactionsJson)
        }
        val json = Json.obj(
          "postId" -> p.id,
          "postContent" -> p.content,
          "commentsCount" -> comments.size,
          "comments" -> commentsWithReactions
        )
        Future.successful(Ok(Json.toJson(ApiResult(result = Some(json)))))
      case None => Future.successful(NotFound(Json.toJson(ApiResult(error = Some("Post not found")))))
  }

  def getPosts: Action[AnyContent] = Action.async { _ =>
    val json = queryService.getPostsIds().map { id =>
      val post = queryService.getPost(id)
      val comments = queryService.getPostComments(id)
      post match
        case Some(p) =>
          val commentsWithReactions = comments.map { c =>
            val reactions = queryService.getCommentReactions(c.id)
            val reactionsJson = Json.toJson(reactions)
            Json.toJson(c).as[JsObject] + ("reactions" -> reactionsJson)
          }
          Json.obj(
            "postId" -> p.id,
            "postContent" -> p.content,
            "commentsCount" -> comments.size,
            "comments" -> commentsWithReactions
          )
        case None => Json.obj()
    }
    Future.successful(Ok(Json.toJson(ApiResult(result = Some(Json.toJson(json))))))
  }
}
