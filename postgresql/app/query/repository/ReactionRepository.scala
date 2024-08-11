package query.repository

import anorm.{SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database

import query.models.Reaction

@Singleton
class ReactionRepository @Inject() (db: Database) {

  private val tableName = "reactions"

  def findByCommentId(commentId: Long): List[Reaction] = db.withConnection { implicit c =>
    SQL"SELECT * FROM #$tableName WHERE comment_id = $commentId".as(Reaction.parser.*)
  }
}
