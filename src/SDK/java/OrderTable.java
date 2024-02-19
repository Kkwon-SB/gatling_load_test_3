package LoadGenerator;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class OrderTable extends Simulation {
    HashMap<String, Integer> skuCodeMap = new HashMap<>();
    Random rand = new Random();
    private final int[] storeList = {3001, 3004, 3007, 3031, 3032, 3036, 3313, 3314, 3315, 3316, 3318, 3319, 3320, 3321, 3322, 3323, 3324, 3325, 3327, 3328, 3331, 3336, 3338, 3339, 3340, 3341, 3343, 3345, 3354, 3358, 3363, 3370, 3372, 3373, 3379, 3388, 3390};
    //sample1000.exsl 기준
    private final Supplier<String> randomValue = () -> RandomStringUtils.randomAlphanumeric(8);
    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:7955")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");
    private LocalDateTime randomDateTime() {
        int months = rand.nextInt(12);
        int days = rand.nextInt(31) + 1;

        return LocalDateTime.now().minusMonths(months).minusDays(days);
    }

    private Map.Entry<String, Integer> chechSkuCode(String ranSkuCode, int ranFixPrice) {
        String skuCode;
        int fixPrice;

        if (skuCodeMap.containsKey(ranSkuCode)) {
            skuCode = ranSkuCode;
            fixPrice = skuCodeMap.get(skuCode);
        } else {
            skuCodeMap.put(ranSkuCode, ranFixPrice);
            skuCode = ranSkuCode;
            fixPrice = ranFixPrice;
        }

        return new AbstractMap.SimpleEntry<>(skuCode, fixPrice);
    }

    private final Iterator<Map<String, Object>> customFeeder =

            //변수들의 이름, 값, 범위는 실시간 주문 데이터_샘플(Data1000)파일을 참고했습니다.

            Stream.generate((Supplier<Map<String, Object>>) () -> {
                String ranSkuCode = String.format(String.valueOf(rand.nextInt((3)+1)));
                int ranFixPrice = rand.nextInt(100) * 100;

                Map.Entry<String, Integer> fixSkuCode = chechSkuCode(ranSkuCode, ranFixPrice);
                String skuCode = fixSkuCode.getKey();
                int fixPrice = fixSkuCode.getValue();

                final int dscnAmnt = (int) (fixPrice * 0.3);
                final int saleAsmnt = (int) (fixPrice - (fixPrice * 0.3));
                final int storeCode = storeList[rand.nextInt(storeList.length)];
                final int posNo = rand.nextInt(3) + 1;
                final int seqNo = rand.nextInt(560) + 1;
                final String usrId = randomValue.get();
                final int saleSeq = rand.nextInt(14) + 1;
                final int sellQnty = rand.nextInt(5) + 1;

                HashMap<String, Object> hmap = new HashMap<>();
                hmap.put("sale_data", DateTimeFormatter.ofPattern("2023MMdd").format(randomDateTime()));
                hmap.put("store_code", storeCode);
                hmap.put("pos_no", posNo);
                hmap.put("seq_no", seqNo);
                hmap.put("usr_id", usrId);
                hmap.put("sku_code", skuCode);
                hmap.put("sale_seq", saleSeq);
                hmap.put("sell_unpr", fixPrice);
                hmap.put("sell_qnty", sellQnty); //계산 포함 x
                hmap.put("sell_amnt", fixPrice);
                hmap.put("dscn_amnt", dscnAmnt);
                hmap.put("sale_asmnt", saleAsmnt);

                return hmap;
            }).iterator();

    private final ScenarioBuilder scn = scenario("Starducks User Info Sending Simulation")
            .feed(customFeeder)
            .exec(http("Send User's Info")
                    .post("/postback/event")
                    .header("Content-Type", "application/json")
                    .body(StringBody(
                            "{"
                                    + "\"sale_data\": \"#{sale_data}\","
                                    + "\"store_code\": \"#{store_code}\","
                                    + "\"pos_no\": \"#{pos_no}\","
                                    + "\"seq_no\": \"#{seq_no}\","
                                    + "\"usr_id\": \"#{usr_id}\","
                                    + "\"sku_code\": \"#{sku_code}\","
                                    + "\"sale_seq\": \"#{sale_seq}\","
                                    + "\"sell_unpr\": \"#{sell_unpr}\","
                                    + "\"sell_qnty\": \"#{sell_qnty}\","
                                    + "\"sell_amnt\": \"#{sell_amnt}\","
                                    + "\"dscn_amnt\": \"#{dscn_amnt}\","
                                    + "\"sale_asmnt\": \"#{sale_asmnt}\""
                                    + "}")
                    ).asJson().check(status().is(200))
            );
    {
        setUp(
                scn.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocol);
    }
}