package command.controllers

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json

import command.models._
import command.services.CommandService

@Singleton
class CommandController @Inject() (cc: ControllerComponents, commandService: CommandService)(
  implicitec: ExecutionContext
) extends AbstractController(cc) {

  def addPost(): Action[Post] = Action(parse.json[Post]) { request =>
    val id = commandService.addPost(request.body)
    Ok(Json.toJson(ApiResult(message = Some(s"Post #$id created"), result = Some(Json.toJson(id)))))
  }

  def addComment(postId: Long): Action[Comment] = Action(parse.json[Comment]) { request =>
    val id = commandService.addComment(request.body, postId)
    Ok(Json.toJson(ApiResult(message = Some(s"Comment #$id created"), result = Some(Json.toJson(id)))))
  }

  def addReaction(postId: Long, commentId: Long): Action[Reaction] = Action(parse.json[Reaction]) { request =>
    val id = commandService.addReaction(request.body, postId: Long, commentId: Long)
    Ok(Json.toJson(ApiResult(message = Some(s"Reaction #$id created"), result = Some(Json.toJson(id)))))
  }

  // def delete(id: Long): Action[AnyContent] = Action { request =>
  //   comments.delete(id)
  //   Ok(Json.toJson(ApiResult(message = Some(s"Comment #$id deleted"), result = Some(Json.toJson(id)))))
  // }

  // def update(): Action[Comment] = Action(parse.json[Comment]) { request =>
  //   val comment = comments.update(request.body)
  //   comment match {
  //     case Some(c) =>
  //       Ok(Json.toJson(ApiResult(message = Some(s"Comment #${c.id.get} updated"), result = Some(Json.toJson(c)))))
  //     case None => NotFound(Json.toJson(ApiResult(error = Some(s"Comment not found"))))
  //   }
  // }

  // def get(id: Long): Action[AnyContent] = Action { request =>
  //   comments.get(id) match
  //     case Some(comment) => Ok(Json.toJson(ApiResult(result = Some(Json.toJson(comment)))))
  //     case None          => NotFound(Json.toJson(ApiResult(error = Some(s"Comment #$id not found"))))
  // }
}
