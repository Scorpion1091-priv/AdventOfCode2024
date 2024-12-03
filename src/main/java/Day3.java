import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    private static final List<String> input = new ArrayList<>();
    private static final List<Mul> muls = new ArrayList<>();
    private static final Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");


    public static void main(String[] args) {

        readInput();

        input.forEach(Day3::findMuls);

        System.out.println("Result is " + sumMuls());

    }

    private static long sumMuls() {

        return muls.stream()
                .map(mul -> mul.num1() * mul.num2())
                .mapToLong(Long::valueOf)
                .sum();

    }

    private static void findMuls(String line) {

        List<String> matches = new ArrayList<>();
        Matcher m = pattern.matcher(line);

        while (m.find()) {
            matches.add(m.group());
        }
        matches.stream()
                .map(mulString -> mulString.substring(4, mulString.length() - 1))
                .map(pair -> pair.split(","))
                .map(pair -> new Mul(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])))
                .forEach(muls::add);
    }


    private static void readInput() {

        ClassLoader classLoader = Day1.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("day3_input.txt");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private record Mul(int num1, int num2) {
    }

    ;
}