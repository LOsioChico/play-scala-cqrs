package query.models

import play.api.libs.json.{JsValue, Json, OFormat}

case class ApiResult(error: Option[String] = None, message: Option[String] = None, result: Option[JsValue] = None)

object ApiResult {
  implicit val format: OFormat[ApiResult] = Json.format[ApiResult]
}
