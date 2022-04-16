import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KnnForcaBruta<T> {

    private int K;
    private List<Penguin> trainingData;
    private Function<Penguin, T> target;


    public KnnForcaBruta(int K, List<Penguin> trainingData, Function<Penguin, T> target) {
        this.K = K;
        this.trainingData = trainingData;
        this.target = target;
    }



    public List<PredictedPenguin<T>> predict(List<Penguin> testData) 
    {

        List<PredictedPenguin<T>> predictions = new ArrayList<>();


        for(Penguin test : testData)
        {
            T prediction = trainingData.stream()
            .map(p -> new PenguinDistance(p, p.getDistance(test)))
            .sorted()
            .limit(K)
            .map(PenguinDistance::getPenguin)
            .map(target)
            .collect(Collectors.groupingBy(a -> a, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .get();

            predictions.add(new PredictedPenguin<T>(test, prediction));
        }

        return predictions;

    }






}