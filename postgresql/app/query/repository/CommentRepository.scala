package query.repository

import anorm.{SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database

import query.models.Comment

@Singleton
class CommentRepository @Inject() (db: Database) {

  private val tableName = "comments"

  def findByPostId(postId: Long): List[Comment] = db.withConnection { implicit c =>
    SQL"SELECT * FROM #$tableName WHERE post_id = $postId".as(Comment.parser.*)
  }
}
