package query.models

import anorm.{Column, Macro, RowParser}
import play.api.libs.json.{Json, OFormat}

case class Reaction(id: Long, emoji: String, postId: Long, commentId: Long)

object Reaction {
  implicit val format: OFormat[Reaction] = Json.format[Reaction]
  val parser: RowParser[Reaction] = Macro.namedParser[Reaction](
    Macro.ColumnNaming.SnakeCase
  )
}
