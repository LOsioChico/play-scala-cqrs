package query.repository

import anorm.{SqlParser, SqlStringInterpolation}
import javax.inject.{Inject, Singleton}
import play.api.db.Database

import query.models.Post

@Singleton
class PostRepository @Inject() (db: Database) {

  private val tableName = "posts"

  def findById(id: Long): Option[Post] = db.withConnection { implicit c =>
    SQL"SELECT * FROM #$tableName WHERE id = $id".as(Post.parser.singleOpt)
  }

  def getPostsIds(): List[Long] = db.withConnection { implicit c =>
    SQL"SELECT id FROM #$tableName".as(SqlParser.long("id").*)
  }
}
