package query.services

import javax.inject.{Inject, Singleton}
import play.api.libs.json.{JsObject, Json}

import query.repository._
import query.models._

@Singleton
class QueryService @Inject() (posts: PostRepository, comments: CommentRepository, reaction: ReactionRepository) {

  def getPostsJson(id: Long): JsObject = posts.findById(id) match
    case Some(p) =>
      val commentsWithReactions = getPostComments(p.id).map { c =>
        val reactions = getCommentReactions(c.id)
        val reactionsJson = Json.toJson(reactions)
        Json.toJson(c).as[JsObject] + ("reactions" -> reactionsJson)
      }
      Json.obj(
        "postId" -> p.id,
        "postContent" -> p.content,
        "commentsCount" -> commentsWithReactions.size,
        "comments" -> commentsWithReactions
      )
    case None => Json.obj()

  def getPostsIds(): List[Long] = posts.getPostsIds()

  def getPostComments(postId: Long): List[Comment] = comments.findByPostId(postId)

  def getCommentReactions(commentId: Long): List[Reaction] = reaction.findByCommentId(commentId)

}
