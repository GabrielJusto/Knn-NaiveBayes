import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws FileNotFoundException {
        List<Penguin> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("penguins.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {

                Penguin p;
                if ((p = getRecordFromLine(scanner.nextLine())) == null)
                    continue;

                records.add(p);
            }


        }

        int pivot = (int) (records.size() * 0.3);

        List<Penguin> thirtyPercent = new ArrayList<>();
        List<Penguin> seventyPercent = new ArrayList<>();

        Collections.shuffle(records);

        for (int i = 0; i < records.size(); i++) {
            if (i <= pivot)
                thirtyPercent.add(records.get(i));
            else
                seventyPercent.add(records.get(i));
        }



        // System.out.println(nb.probability("Adelie Penguin (Pygoscelis adeliae)"));
       
        NaiveBayes<String> knn = new NaiveBayes<>(seventyPercent, Penguin::getIsland);
        // KnnForcaBruta<String> knn = new KnnForcaBruta<String>(5, seventyPercent, Penguin::getSpecies);

        // KdTree<String> knn = new KdTree<>(seventyPercent, Penguin::getSpecies);
        List<PredictedPenguin<String>> predictedList = knn.predict(thirtyPercent);

        System.out.println(
                predictedList.stream()
                        .map(pd -> pd.getPenguin().getIsland().equals(pd.getPredictedClass()))
                        .filter(pd -> pd)
                        .count());

        System.out.println(predictedList.size());
    }

    private static Penguin getRecordFromLine(String line)
    {

        String[] propertiesStr = line.split(",");
        try
        {
            return new Penguin(propertiesStr[0], parseInteger(propertiesStr[1]), propertiesStr[2],
                    propertiesStr[3], propertiesStr[4], propertiesStr[5], propertiesStr[6],
                    Boolean.valueOf(propertiesStr[7]), LocalDate.parse(propertiesStr[8]),
                    parseFloat(propertiesStr[9]), parseFloat(propertiesStr[10]),
                    parseFloat(propertiesStr[11]), parseInteger(propertiesStr[12]),
                    propertiesStr[13].equals("MALE") ? Sex.MALE : Sex.FEMALE,
                    parseFloat(propertiesStr[14]), parseFloat(propertiesStr[15]),
                    propertiesStr[16]);
        } catch (NumberFormatException e)
        {
            return null;
        }
    }

    private static Float parseFloat(String value) throws NumberFormatException
    {

        return Float.valueOf(value);

    }

    private static Integer parseInteger(String value) throws NumberFormatException
    {

        return Integer.valueOf(value);

    }
}
