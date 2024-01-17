package SDK_TEST;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostExample {

    public static void main(String[] args) {
        String url = "http://localhost:5000/api/videogame";

        // POST 요청에 사용할 JSON 데이터
        String postData = "{"
                + "\"gameId\": 1,"
                + "\"gameName\": \"SampleGame\","
                + "\"releaseDate\": \"2024-01-01\","
                + "\"reviewScore\": 85,"
                + "\"category\": \"Action\","
                + "\"rating\": \"E\""
                + "}";

        try {
            // URL 객체 생성
            URL obj = new URL(url);
            // HttpURLConnection 객체 생성
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // 요청 메서드 설정
            con.setRequestMethod("POST");
            // 헤더 설정
            con.setRequestProperty("Content-Type", "application/json");
            // POST 데이터를 전송 가능하도록 설정
            con.setDoOutput(true);
            // POST 데이터를 출력 스트림을 통해 전송
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = con.getResponseCode();
            System.out.println("Status Code: " + responseCode);

            // 응답 데이터 읽기
            try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // 응답 내용 출력
                System.out.println("Response Body: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
