//package videogamedb.scriptfundamentals;
//
//// 패키지 및 임포드 - 클래스의 패키지를 정의하고 Gatling, 웹 애플리케이션 성능 테스트 도구에 필요한 클래스를 가져온다
//import io.gatling.javaapi.core.*;
//import io.gatling.javaapi.http.*;
//
//import java.time.Duration;
//import java.util.List;
//
//import static io.gatling.javaapi.core.CoreDsl.*;
//import static io.gatling.javaapi.http.HttpDsl.*;
//
//
//public class VideoGameDb extends Simulation {
//
//    private HttpProtocolBuilder httpProtocol = http
//            .baseUrl("https://videogamedb.uk/api")
//            .acceptHeader("application/json");
//
//    private ScenarioBuilder scn = scenario("Video Game Db - Section 5 Code")
//            /*
//            .exec(http("Get All Games - 1st call")
//                    .get("/videogame")
//                    .check(status().is(200))
//                    .check(jmesPath("[? id == `1`].name").ofList().is(List.of("Resident Evil 4")))) // 결과를 리스트로 받아옴
//
//            .pause(5)
////                    .check(jmesPath("[? id == `1`].name").is("Resident Evil 4"))) //결과가 정확히 문자와 일치해야 함
////                    .check(jmesPath("[? id == `1`].name").is("Resident Evil 4")))
////                    .check(jsonPath("$[?(@.id==1)].name").is("Resident Evil 5")))
//            */
//            .exec(http("Get Specific Game")
//                    .get("/videogame/1")
//                    .check(status().in(200, 201, 202)))
//            .pause(1, 10) //1 ~ 10초 사이 무작위 일시 중지, 현실성 부여
//
//
//
//            .exec(http("Get All Games")
//                    .get("/videogame")
//                    .check(status().not(404), status().not(500))
//                    .check(jmesPath("[1].id").saveAs("gameId"))) //두 번째 아이디 이름을 'gameId'로 저장
//            .pause(Duration.ofMillis(4000))
///*
//            .exec(
//                    session -> {
//                        System.out.println(session);
//                        System.out.println("Game ID set to : " + session.getString("gameId"));
//                        return session;
//                    }
//            )
//*/
//            .exec(http("Get specific game with Id - #{gameId}")
//                    .get("/videogame/#{gameId}")
//                    .check(jmesPath("name").is("Gran Turismo 3"))
//                    .check(bodyString().saveAs("responseBody")))
//            .exec(
//                    session -> {
//                        System.out.println("Response Body : " + session.getString("responseBody"));
//                        return session;
//                    }
//            );
//
//
//    {
//        setUp(
//                scn.injectOpen(atOnceUsers(1))
//        ).protocols(httpProtocol);
//    }
//
//
//}
