package LoadGenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class getUserEvent extends Simulation {
    private String[] eventNames = {"Main_BottomNavigation", "Home_imp", "Order_category_imp", "order_subcategory", "order_category_selectsku", "home_notice_close", "order_detail_sirenorder", "abx:purchase", "Home_orderstatus_imp", "order_category_back"};
    private Random rand = new Random();
    int randomInt = this.rand.nextInt(100);
    private Supplier<String> randomValue = () -> RandomStringUtils.randomAlphanumeric(8);
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:7955")
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

    private String getEventParamMap(String eventName) throws JsonProcessingException {
        Map<String, String> paramMap = new HashMap<>();

        switch (eventName) {

            case "Main_BottomNavigation":
                paramMap.put("event_name","Main_BottomNavigation");
                paramMap.put("page_type", randomValue.get());
                paramMap.put("screen_name", randomValue.get());
                break;

            case "Home_imp":
                paramMap.put("event_name", "Home_imp");
                paramMap.put("Seq", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("content_id", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("content_name", randomValue.get());
                break;

            case "Order_category_imp":
                paramMap.put("event_name", "Order_category_imp");
                paramMap.put("main_category", rand.nextBoolean() ? "음료" : "푸드");
                paramMap.put("sub_category", rand.nextBoolean() ? "Y" : "N");
                break;

            case "order_subcategory":
                paramMap.put("event_name", "order_subcategory");
                paramMap.put("sub_category", randomValue.get());
                paramMap.put("Sub_category_no", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("Non_member_yn", rand.nextBoolean() ? "Y" : "N");
                paramMap.put("Green_dot_yn", rand.nextBoolean() ? "Y" : "N");
                break;

            case "order_category_selectsku":
                paramMap.put("event_name", "order_category_selectsku");
                paramMap.put("sku_name", randomValue.get());
                break;

            case "home_notice_close":
                paramMap.put("event_name", "home_notice_close");
                paramMap.put("Content_name", randomValue.get());
                break;

            case "order_detail_sirenorder":
                paramMap.put("event_name", "order_detail_sirenorder");
                paramMap.put("sku_name", randomValue.get());
                paramMap.put("sku_no", String.valueOf(randomInt - randomInt % 10));
                break;

            case "abx:purchase":
                paramMap.put("event_name", "abx:purchase");
                paramMap.put("Skuinfo", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("Qty", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("ReceiveData", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(randomDateTime()));
                paramMap.put("ReceiveStore", randomValue.get());
                paramMap.put("PatmentType", rand.nextBoolean() ? "card" : "cash");
                paramMap.put("PaymentPrice", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("UsedCoupon", rand.nextBoolean() ? "Y" : "N");
                paramMap.put("UsedCouponPrice", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("UsedGift", rand.nextBoolean() ? "Y" : "N");;
                paramMap.put("UsedGiftPrice", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("OrderPrice", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("DiscountType", rand.nextBoolean() ? "Y" : "N");
                paramMap.put("DiscountPrice", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("TotalOrderPrice", String.valueOf(randomInt - randomInt % 10));
                paramMap.put("ReceipYN", rand.nextBoolean() ? "Y" : "N");
                break;

            case "Home_orderstatus_imp":
                paramMap.put("event_name", "Home_orderstatus_imp");
                paramMap.put("order_type", rand.nextBoolean() ? "매장" : "차량");
                paramMap.put("Store_name", randomValue.get());
                paramMap.put("eating_type", rand.nextBoolean() ? "매장" : "포장");
                break;

            case "order_category_back":
                paramMap.put("event_name", "order_category_back");
                paramMap.put("main_category", rand.nextBoolean() ? "음료" : "푸드");
                paramMap.put("sub_category", rand.nextBoolean() ? "Y" : "N");
                break;
        }
        return this.objectMapper.writeValueAsString(paramMap);
    }

    private Iterator<Map<String, Object>> customFeeder =
            Stream.generate((Supplier<Map<String, Object>>) () -> {

                String eventName = this.eventNames[rand.nextInt(10)];
                String eventParamString = "{}";
                try {
                    eventParamString = this.getEventParamMap(eventName);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                System.out.println(eventParamString);
                HashMap<String, Object> hmap = new HashMap<>();
                hmap.put("event_param", eventParamString);
                return hmap;
            }).iterator();

    private ScenarioBuilder scn = scenario("Starducks User Info Sending Simulation")

            .feed(customFeeder)
            .exec(http("Send User's Info")
                    .post("/postback/event")
                    .header("Content-Type", "application/json")
                    .body(StringBody(
                            "{"
                                    + "\"Data\": #{event_param}"
                                    + "}")
                    ).asJson().check(status().is(200))
            );
    {
        setUp(
                scn.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocol);
    }
}