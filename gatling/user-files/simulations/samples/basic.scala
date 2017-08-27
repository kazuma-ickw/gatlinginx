package samples

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SampleSimulation extends Simulation {

  // 環境変数でリクエスト先、時間(秒)、ユーザー数を設定する。(ユーザー数/時間が秒間リクエスト数)
  val baseUrl = scala.sys.env.getOrElse("BASE_URL","http://localhost") // http://localhost
  val targetPath = scala.sys.env.getOrElse("TARGET_PATH", "/")         // / に
  val howLongSeconds = scala.sys.env.getOrElse("SECONDS", "5")         // 5秒間で
  val howManyUsers = scala.sys.env.getOrElse("USERS", "10")            // 10ユーザーがリクエストする

  val httpConf = http
    .baseURL(baseUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation")
    .exec(http("request_1")
    .get(targetPath))

  setUp(
    scn.inject(rampUsers(howManyUsers.toInt) over (howLongSeconds.toInt seconds))
  ).protocols(httpConf)
}
