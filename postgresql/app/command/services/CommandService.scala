package command.services

import javax.inject.{Inject, Singleton}

import command.repository._
import command.models._

@Singleton
class CommandService @Inject() (posts: PostRepository, comments: CommentRepository, reactions: ReactionRepository) {

  def addPost(post: Post): Int = posts.save(post)

  def addComment(comment: Comment, postId: Long): Int = comments.save(comment, postId)

  def addReaction(reaction: Reaction, postId: Long, commentId: Long): Int =
    reactions.save(reaction, postId: Long, commentId: Long)

}
