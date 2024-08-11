package command.repository

import anorm.{SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database

import command.models.Post

@Singleton
class PostRepository @Inject() (db: Database) {

  private val tableName = "posts"

  def save(post: Post): Int = db.withConnection { implicit c =>
    SQL"""
      INSERT INTO #$tableName (content)
      VALUES (${post.content})
      RETURNING id
      """.as(SqlParser.int("id").single)
  }
}
