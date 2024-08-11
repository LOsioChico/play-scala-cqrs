package query.services

import javax.inject.{Inject, Singleton}

import query.repository._
import query.models._

@Singleton
class QueryService @Inject() (posts: PostRepository, comments: CommentRepository, reaction: ReactionRepository) {

  def getPost(id: Long): Option[Post] = posts.findById(id)

  def getPostsIds(): List[Long] = posts.getPostsIds()

  def getPostComments(postId: Long): List[Comment] = comments.findByPostId(postId)

  def getCommentReactions(commentId: Long): List[Reaction] = reaction.findByCommentId(commentId)

}
