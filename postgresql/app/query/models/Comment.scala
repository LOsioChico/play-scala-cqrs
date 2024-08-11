package query.models

import anorm.{Column, Macro, RowParser}
import play.api.libs.json.{Json, OFormat}

case class Comment(id: Long, content: String, postId: Long)

object Comment {
  implicit val format: OFormat[Comment] = Json.format[Comment]
  val parser: RowParser[Comment] = Macro.namedParser[Comment](
    Macro.ColumnNaming.SnakeCase
  )
}
