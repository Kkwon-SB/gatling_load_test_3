//
//// 패키지 및 임포드 - 클래스의 패키지를 정의하고 Gatling, 웹 애플리케이션 성능 테스트 도구에 필요한 클래스를 가져온다
//package videogamedb.scriptfundamentals;
//
//import io.gatling.javaapi.core.*;
//import io.gatling.javaapi.http.*;
//import static io.gatling.javaapi.core.CoreDsl.*;
//import static io.gatling.javaapi.http.HttpDsl.*;
//
//
//
////클래스 선언 - MyFirstClass라는 클래스를 선언하며, 이 클래스는 Simulation을 확장합니다. Gatling에서는 시뮬레이션을 사용하여 성능 테스트를 정의하고 실행한다.
//public class MyFirstClass extends Simulation { //Simulation 클래스는 게틀링 프레임워크에서 제공하는 클래스, 게클링 라이브러리에 저장.시뮬레이션 정의를 위한 기본 특정, 메서트 포함하고 있다.
//
//    // 1 http Configuration [ 기본 URL설정 , 각 http의 헤더 설정 ]
//    // HttpProtocolBuilder인 httpProtocol을 정의하며, API의 기본 URL(https://videogamedb.uk/api)을 설정하고 Accept 헤더를 JSON으로 지정
//    private HttpProtocolBuilder httpProtocol = http
//            .baseUrl("https://videogamedb.uk/api")
//            .acceptHeader("application/json");
//
//
//
//    // 2 Scenario Definition [ 사용자가 스크립트를 통해 얻는 사용자 경험 ]
//    // ScenarioBuilder(scn)를 사용하여 "My First Test"라는 시나리오를 생성, 시나리오 내에서 exec 메서드를 사용하여 HTTP 요청이 정의된다.
//    // 이 요청은 /videogame 경로로의 GET 요청이다.
//    private ScenarioBuilder scn = scenario("My First Test")
//            .exec(http("Get All Games")
//                    .get("/videogame"));
//
//
//
//    // 3 Load Simulation [ 부하 시물레이션 : 사용자 수, 테스트 시간, ]
//    // setUp 메서드를 사용하여 시나리오에 대한 인젝션 프로파일을 정의한다.
//    // 여기서는 시뮬레이션 시작 시에 하나의 사용자(atOnceUsers(1))를 인젝션한다.
//    // protocols 메서드는 정의된 httpProtocol을 시뮬레이션과 연결한다.
//
//    /*
//    인젝션 프로파일(Injection Profile)은
//    Gatling에서 성능 시뮬레이션을 실행할 때 시뮬레이션에 참여하는 사용자들이 어떻게 시간에 따라 시뮬레이션에 추가되거나 제거될지를 정의하는 방법.
//    이는 사용자들을 언제, 어떻게, 얼마나 많이 시뮬레이션에 주입할지에 대한 규칙을 나타낸다.
//     */
//    {
//        setUp(
//                scn.injectOpen(atOnceUsers(1))
//        ).protocols(httpProtocol);
//    }
//
//
//}
