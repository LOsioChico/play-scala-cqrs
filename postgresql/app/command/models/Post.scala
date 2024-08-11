package command.models

import anorm.{Macro, RowParser}
import play.api.libs.json.{Json, OFormat}

case class Post(id: Option[Long], content: String)

object Post {
  implicit val format: OFormat[Post] = Json.format[Post]
  val parser: RowParser[Post] = Macro.namedParser[Post]
}
