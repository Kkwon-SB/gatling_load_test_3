//package videogamedb.feeders;
//
//import io.gatling.javaapi.core.Simulation;
//import org.apache.commons.lang3.RandomStringUtils;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Random;
//import java.util.function.Supplier;
//import java.util.stream.Stream;
//
//public class Main extends Simulation {
//    private static Iterator<Map<String, Object>> customFeeder =
//            Stream.generate((Supplier<Map<String, Object>>) () -> {
//                        Random rand = new Random();
//
//                        String adid = RandomStringUtils.randomAlphanumeric(20);
//                        String gaid = RandomStringUtils.randomAlphanumeric(36);
//                        String idfa = RandomStringUtils.randomNumeric(8) + "-"
//                                + RandomStringUtils.randomNumeric(4) + "-"
//                                + RandomStringUtils.randomNumeric(4) + "-"
//                                + RandomStringUtils.randomNumeric(4) + "-"
//                                + RandomStringUtils.randomNumeric(12);
//                        String idfv = RandomStringUtils.randomAlphanumeric(24);
//                        boolean adIdOptOut = rand.nextBoolean();
//                        String os = "Android 11.0";
//                        String model = "Samsung Galaxy S20";
//                        String vendor = "Samsung";
//                        String resolution = "1440x3200";
//                        boolean isPortrait = rand.nextBoolean();
//                        String platform = "Android";
//                        String network = "Wi-Fi";
//                        boolean isWifiOnly = rand.nextBoolean();
//                        String carrier = "Verizon";
//                        String language = "en-US";
//                        String country = "US";
//                        String buildId = "RP1A.200720.012";
//                        String packageName = "com.example.android.app";
//                        String appKey = RandomStringUtils.randomAlphanumeric(20);
//                        String sdkVersion = "1.2.3";
//                        String installer = "Google Play Store";
//                        String appVersion = "2.0.1";
//                        String eventName = "App Open";
//                        String group = "User Engagement";
//                        LocalDateTime eventDateTime = LocalDateTime.now();
//                        long eventTimestamp = System.currentTimeMillis() / 1000;
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//                        String eventDatetimeKST = eventDateTime.format(formatter);v
//                        long eventTimestampKST = eventTimestamp + 9 * 60 * 60; // Adding 9 hours for KST
//                        long eventTimestampDKST = eventTimestamp + 9 * 60 * 60;
//
//                        String orderId = "ORD" + RandomStringUtils.randomNumeric(6);
//                        double orderSales = rand.nextDouble() * 100;
//
//                        HashMap<String, Object> hmap = new HashMap<>();
//                        hmap.put("adid", adid);
//                        hmap.put("gaid", gaid);
//                        hmap.put("idfa", idfa);
//                        hmap.put("idfv", idfv);
//                        hmap.put("ad_id_opt_out", adIdOptOut);
//                        hmap.put("os", os);
//                        hmap.put("model", model);
//                        hmap.put("vendor", vendor);
//                        hmap.put("resolution", resolution);
//                        hmap.put("is_portrait", isPortrait);
//                        hmap.put("platform", platform);
//                        hmap.put("network", network);
//                        hmap.put("is_wifi_only", isWifiOnly);
//                        hmap.put("carrier", carrier);
//                        hmap.put("language", language);
//                        hmap.put("country", country);
//                        hmap.put("build_id", buildId);
//                        hmap.put("package_name", packageName);
//                        hmap.put("appkey", appKey);
//                        hmap.put("sdk_version", sdkVersion);
//                        hmap.put("installer", installer);
//                        hmap.put("app_version", appVersion);
//                        hmap.put("event_name", eventName);
//                        hmap.put("group", group);
//                        hmap.put("event_datetime", eventDateTime);
//                        hmap.put("event_timestamp", eventTimestamp);
//                        hmap.put("event_timestamp_d", eventTimestamp);
//                        hmap.put("event_datetime_kst", eventDatetimeKST);
//                        hmap.put("event_timestamp_kst", eventTimestampKST);
//                        hmap.put("event_timestamp_d_kst", eventTimestampDKST);
//                        hmap.put("abx:order_id", orderId);
//                        hmap.put("abx:order_sales", orderSales);
//
//                        return hmap;
//                    }
//            ).iterator();
//
//    public static void main(String[] args) {
//        // Example usage:
//        while (customFeeder.hasNext()) {
//            System.out.println(customFeeder.next());
//        }
//    }
//}
