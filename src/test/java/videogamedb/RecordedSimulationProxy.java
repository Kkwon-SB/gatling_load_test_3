package videogamedb;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulationProxy extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://videogamedb.uk")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("PostmanRuntime/7.36.0");
  
  private Map<CharSequence, String> headers_0 = Map.of("Postman-Token", "32dcddbb-5afb-4d4b-ba3d-929c95d80688");
  
  private Map<CharSequence, String> headers_1 = Map.of("Postman-Token", "8f284ffc-433a-4e8c-879b-f4d01b556adb");
  
  private Map<CharSequence, String> headers_2 = Map.of("Postman-Token", "8df31a06-10fc-4daa-87ef-588673286459");
  
  private Map<CharSequence, String> headers_3 = Map.of("Postman-Token", "db887714-3e69-43e9-ab48-26942ae3bcb5");
  
  private Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "12b53207-578c-4a1e-8794-37d09495be2a")
  );
  
  private Map<CharSequence, String> headers_5 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "489499fb-208d-466b-a414-3d28120bcb23"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwNDg3Njg5NSwiZXhwIjoxNzA0ODgwNDk1fQ.CuQkGZTBxn_aWhqGhRAOEUZ5t82alrup9Z_yJ1YFg70")
  );
  
  private Map<CharSequence, String> headers_6 = Map.ofEntries(
    Map.entry("Postman-Token", "3ab2ef61-55b1-4f7c-8d29-a12ec502b898"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwNDg3Njg5NSwiZXhwIjoxNzA0ODgwNDk1fQ.CuQkGZTBxn_aWhqGhRAOEUZ5t82alrup9Z_yJ1YFg70")
  );


  private ScenarioBuilder scn = scenario("RecordedSimulationProxy")
    .exec(
      http("request_0")
        .get("/api/videogame")
        .headers(headers_0),
      pause(19),
      http("request_1")
        .get("/api/videogame")
        .headers(headers_1),
      pause(12),
      http("request_2")
        .get("/api/videogame")
        .headers(headers_2),
      pause(1593),
      http("request_3")
        .get("/api/videogame/2")
        .headers(headers_3),
      pause(753),
      http("request_4")
        .post("/api/authenticate")
        .headers(headers_4)
        .body(RawFileBody("videogamedb/recordedsimulationproxy/0004_request.json")),
      pause(56),
      http("request_5")
        .put("/api/videogame/3")
        .headers(headers_5)
        .body(RawFileBody("videogamedb/recordedsimulationproxy/0005_request.json")),
      pause(3),
      http("request_6")
        .delete("/api/videogame/2")
        .headers(headers_6)
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
