package command.models

import anorm.{Macro, RowParser}
import play.api.libs.json.{Json, OFormat}

case class Reaction(id: Option[Long], emoji: String, postId: Option[Long], commentId: Option[Long])

object Reaction {
  implicit val format: OFormat[Reaction] = Json.format[Reaction]
  val parser: RowParser[Reaction] = Macro.namedParser[Reaction]
}
