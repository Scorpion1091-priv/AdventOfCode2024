
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final List<Integer> list1 = new ArrayList<>();
    private static final List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {

        String filename = args == null || args.length == 0 || args[0].isBlank() ? "AdventOfCode2024_1_input.csv" : args[0];

        readLists(filename);

        System.out.println("DistanceScore: " + distance());
        System.out.println("SimilarityScore: " + similarity());
    }

    private static Long similarity() {
        long similarity = 0;
        for (Integer id : list1) {
            long appearance = list2.stream().filter(num -> Objects.equals(num, id)).count();
            similarity += id * appearance;
        }
        return similarity;
    }

    private static void readLists(String filename) {
        ClassLoader classLoader = Main.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filename);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    list1.add(Integer.valueOf(values[0]));
                    list2.add(Integer.valueOf(values[1]));
                } else {
                    System.out.println("found to much values: " + String.join(", ", values));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(list1);
        Collections.sort(list2);
    }

    private static Long distance() {
        long distance = 0;
        for (int i = 0; i< list1.size(); i++) {
            distance += Math.abs(list1.get(i) - list2.get(i));
        }
        return distance;
    }

}
