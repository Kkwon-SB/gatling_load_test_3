package store_tutorial;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class MyFirstTest extends Simulation {

    // 1 Http Configuration
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:5000//starducks")
            .acceptHeader("application/json");

    // 2 Scenario Definition
    private ScenarioBuilder scn = scenario("input_user_info")
            .exec(http("input_user_info")
                    .post("/user_order"));

    // 3 Load Simulation
    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}