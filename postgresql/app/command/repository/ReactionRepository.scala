package command.repository

import anorm.{SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database

import command.models.Reaction

@Singleton
class ReactionRepository @Inject() (db: Database) {

  private val tableName = "reactions"

  def save(reaction: Reaction, postId: Long, commentId: Long): Int = db.withConnection { implicit c =>
    SQL"""
      INSERT INTO #$tableName (emoji, postId, commentId)
      VALUES (${reaction.emoji}, $postId, $commentId)
      RETURNING id
      """.as(SqlParser.int("id").single)
  }
}
