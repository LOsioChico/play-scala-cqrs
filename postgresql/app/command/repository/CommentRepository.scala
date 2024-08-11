package command.repository

import anorm.{SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database

import command.models.Comment

@Singleton
class CommentRepository @Inject() (db: Database) {

  private val tableName = "comments"

  def save(comment: Comment, postId: Long): Int = db.withConnection { implicit c =>
    SQL"""
      INSERT INTO #$tableName (content, postId)
      VALUES (${comment.content}, $postId)
      RETURNING id
      """.as(SqlParser.int("id").single)
  }
}
