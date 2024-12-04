import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {

    private static final List<String> input = new ArrayList<>();
    private static final List<Mul> muls = new ArrayList<>();
    private static final Pattern mul_pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
    private static final Pattern do_pattern = Pattern.compile("do\\(\\)");
    private static final Pattern dont_pattern = Pattern.compile("don't\\(\\)");
    private static boolean mulEnabled = true;


    public static void main(String[] args) {

        readInput();

        input.forEach(Day3::findMuls);

        System.out.println("Result is " + sumMuls());

        muls.clear();
        input.forEach(Day3::findMulsPart2);

        System.out.println("Result with do and dont is " + sumMuls());

    }


    private static long sumMuls() {

        return muls.stream()
                .map(mul -> mul.num1() * mul.num2())
                .mapToLong(Long::valueOf)
                .sum();

    }

    private static void findMuls(String line) {

        List<String> matches = new ArrayList<>();
        Matcher m = mul_pattern.matcher(line);

        while (m.find()) {
            matches.add(m.group());
        }
        matches.stream()
                .map(mulString -> mulString.substring(4, mulString.length() - 1))
                .map(pair -> pair.split(","))
                .map(pair -> new Mul(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])))
                .forEach(muls::add);
    }

    private static void findMulsPart2(String line) {


        Matcher mul_matcher = mul_pattern.matcher(line);
        Matcher do_matcher = do_pattern.matcher(line);
        Matcher dont_matcher = dont_pattern.matcher(line);

        List<MatchWithPosition> multiplications = getMatchesWithPositions(mul_matcher);
        List<MatchWithPosition> donts = getMatchesWithPositions(dont_matcher);
        List<MatchWithPosition> dos = getMatchesWithPositions(do_matcher);

        List<String> words = Stream.concat(Stream.concat(multiplications.stream(), dos.stream()), donts.stream()).sorted().map(MatchWithPosition::value).toList();

        for(String word : words) {
            if (word.startsWith("mul") && mulEnabled) {
                String[] pair = word.substring(4, word.length() - 1).split(",");
                muls.add(new Mul(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
            } else if ("do()".equals(word)) {
                mulEnabled = true;
            } else if ("don't()".equals(word)) {
                mulEnabled = false;

            }
        }


    }

    private static List<MatchWithPosition> getMatchesWithPositions(Matcher matcher) {
        List<MatchWithPosition> result = new ArrayList<>();

        while (matcher.find()) {
            result.add(new MatchWithPosition(matcher.group(), matcher.end()));
        }

        return result;
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

    private record MatchWithPosition(String value, int position) implements Comparable<MatchWithPosition> {
        @Override
        public int compareTo(MatchWithPosition other) {
            return Integer.compare(this.position, other.position);
        }
    }
}