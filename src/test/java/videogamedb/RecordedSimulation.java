package videogamedb;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://www.videogamedb.uk")
    .inferHtmlResources()
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("sec-ch-ua", "Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_5 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://www.videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("sec-ch-ua", "Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_6 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://www.videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwNDg3MTY0NiwiZXhwIjoxNzA0ODc1MjQ2fQ.VGkjDeyBMMLRnq-V0k_meRs-Nif2GWWeva8C94RMus4"),
    Map.entry("sec-ch-ua", "Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );


  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get("/api/videogame")
        .headers(headers_0),
      pause(7),
      http("request_1")
        .get("/api/videogame")
        .headers(headers_0)
        .resources(
          http("request_2")
            .get("/api/videogame")
            .headers(headers_0)
        ),
      pause(76),
      http("request_3")
        .get("/api/videogame/15")
        .headers(headers_0)
        .check(status().is(404)),
      pause(4),
      http("request_4")
        .get("/api/videogame/7")
        .headers(headers_0),
      pause(19),
      http("request_5")
        .post("/api/authenticate")
        .headers(headers_5)
        .body(RawFileBody("videogamedb/recordedsimulation/0005_request.json")),
      pause(64),
      http("request_6")
        .post("/api/videogame")
        .headers(headers_6)
        .body(RawFileBody("videogamedb/recordedsimulation/0006_request.json")),
      pause(12),
      http("request_7")
        .get("/api/videogame")
        .headers(headers_0),
      pause(21),
      http("request_8")
        .post("/api/videogame")
        .headers(headers_6)
        .body(RawFileBody("videogamedb/recordedsimulation/0008_request.json")),
      pause(5),
      http("request_9")
        .get("/api/videogame")
        .headers(headers_0)
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
