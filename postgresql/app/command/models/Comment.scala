package command.models

import anorm.{Macro, RowParser}
import play.api.libs.json.{Json, OFormat}

case class Comment(id: Option[Long], content: String, postId: Option[Long])

object Comment {
  implicit val format: OFormat[Comment] = Json.format[Comment]
  val parser: RowParser[Comment] = Macro.namedParser[Comment]
}
