import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    private static final List<Rule> rules = new ArrayList<>();
    private static final List<List<Integer>> updates = new ArrayList<>();
    private static final List<List<Integer>> validUpdates = new ArrayList<>();
    private static final List<List<Integer>> invalidUpdates = new ArrayList<>();

    public static void main(String[] args) {
        readPrintInstructions();

        findValidUpdates();

        System.out.println(sumMiddlePages(validUpdates));

        fixInvalidUpdates();

        System.out.println(sumMiddlePages(invalidUpdates));

    }

    private static void fixInvalidUpdates() {



    }

    private static Integer sumMiddlePages(List<List<Integer>> updatesToSum) {

        return updatesToSum.stream().map(list -> {int index = (list.size() - 1) / 2; return list.get(index); }).mapToInt(Integer::intValue).sum();
    }

    private static void findValidUpdates() {

        updates.forEach(Day5::checkUpdate);
    }

    private static void checkUpdate(List<Integer> update) {

        boolean isValid = true;
        for (Rule rule : rules) {
            if (update.contains(rule.prev()) && update.contains(rule.post())) {
                int prev = update.indexOf(rule.prev());
                int post = update.indexOf(rule.post());
                if (prev >= post) {
                    isValid = false;
                    invalidUpdates.add(update);
                    break;
                }
            }
        }

        if (isValid) {
            validUpdates.add(update);
        }

    }

    private static void readPrintInstructions() {

        ClassLoader classLoader = Day1.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("day5_input.txt");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            boolean allRulesRead = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().isBlank()) {allRulesRead=true; continue;}

                if(!allRulesRead && line.contains("|")) {
                    String[] parts = line.split("\\|");
                    rules.add(new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                }

                if(allRulesRead) {
                    updates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private record Rule(int prev, int post) {}

}

