package SDK_TEST;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class ExampleFeeder extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:5000/starducks")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    private LocalDateTime randomDateTime() {
        Random rand = new Random();
        int days = rand.nextInt(365);
        int hours = rand.nextInt(24);
        int minutes = rand.nextInt(60);
        int seconds = rand.nextInt(60);

        return LocalDateTime.now().minusDays(days).minusHours(hours).minusMinutes(minutes).minusSeconds(seconds);
    }

    private Iterator<Map<String, Object>> customFeeder =
            Stream.generate((Supplier<Map<String, Object>>) () -> {
                Random rand = new Random();

                Supplier<String> randomValue = () -> RandomStringUtils.randomAlphanumeric(8);

                HashMap<String, Object> hmap = new HashMap<>();
                hmap.put("adid", randomValue.get());
                hmap.put("gaid", randomValue.get());
                hmap.put("idfa", randomValue.get());
                hmap.put("idfv", randomValue.get());
                hmap.put("ad_id_opt_out", rand.nextBoolean());
                hmap.put("os", "Android " + rand.nextInt(10));
                hmap.put("model", randomValue.get());
                hmap.put("vendor", randomValue.get());
                hmap.put("resolution", rand.nextInt(1920) + "x" + rand.nextInt(1080));
                hmap.put("is_portrait", rand.nextBoolean());
                hmap.put("platform", rand.nextBoolean() ? "Android" : "iOS");
                hmap.put("network", rand.nextBoolean() ? "Wi-Fi" : "Cellular");
                hmap.put("is_wifi_only", rand.nextBoolean());
                hmap.put("carrier", randomValue.get());
                hmap.put("language", randomValue.get());
                hmap.put("country", randomValue.get());
                hmap.put("build_id", randomValue.get());
                hmap.put("package_name", randomValue.get());
                hmap.put("appkey", randomValue.get());
                hmap.put("sdk_version", randomValue.get());
                hmap.put("installer", randomValue.get());
                hmap.put("app_version", rand.nextInt(10) + "." + rand.nextInt(10) + "." + rand.nextInt(10));
                hmap.put("event_name", randomValue.get());
                hmap.put("group", randomValue.get());
                hmap.put("event_datetime", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(randomDateTime()));
                hmap.put("event_timestamp", System.currentTimeMillis());
                hmap.put("event_timestamp_d", System.currentTimeMillis());
                hmap.put("event_datetime_kst", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(randomDateTime()));
                hmap.put("event_timestamp_kst", System.currentTimeMillis());
                hmap.put("event_timestamp_d_kst", System.currentTimeMillis());
                hmap.put("abx:order_id", randomValue.get());
                hmap.put("abx:order_sales", rand.nextInt(1000));

                return hmap;
            }).iterator();

    private ChainBuilder createNewGame =
            feed(customFeeder)
                    .exec(http("Send User's Info")
                            .post("/user_order"))
                    .exec(session -> {
                        System.out.println(session.getString("responseBody"));
                        return session;
                    });

    private ScenarioBuilder scn = scenario("Starducks User Info Sending Simulation")
            .exec(createNewGame);

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}
