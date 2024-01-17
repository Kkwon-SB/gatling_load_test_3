package SDK_TEST;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomWordGenerator {

    public static String generateRandomWord(int length) {
        // 영문 알파벳으로 이루어진 무작위 문자열 생성
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static void main(String[] args) {
        // 무작위 단어 생성 및 출력 (예: 길이가 8인 단어)
        String randomWord = generateRandomWord(8);
        System.out.println("Random Word: " + randomWord);
    }
}
