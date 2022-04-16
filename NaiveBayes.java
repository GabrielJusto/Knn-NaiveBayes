import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NaiveBayes<T>

{
    private List<Penguin> penguins;
    private Map<T, Long> groupsMap;
    private Map<T, List<Double>> means;
    private Map<T, List<Double>> variance;
    private Map<T, Double> groupsProb;
    private Function<Penguin, T> target;

    public NaiveBayes(List<Penguin> penguins, Function<Penguin, T> target)
    {
        this.penguins = penguins;
        this.target = target;

        groupsProb = new HashMap<>();

        groupsMap = penguins.stream().map(target)
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));

        for (T k : groupsMap.keySet())
            groupsProb.put(k, groupsMap.get(k) / (double) penguins.size());

        int nFeatures = penguins.get(0).getNumericValues().size();

        means = new HashMap<>();

        for (Penguin p : penguins)
        {
            List<Number> numericValues = p.getNumericValues();
            T group = target.apply(p);

            for (int i = 0; i < nFeatures; i++)
            {
                if (!means.keySet().contains(group))  
                    means.put(group, new ArrayList<>(Collections.nCopies(nFeatures, 0.0)));

                


                List<Double> listAux = means.get(group);
                listAux.set(i, listAux.get(i) + numericValues.get(i).doubleValue());
            }
        }

        for (T k : means.keySet())
        {
            for (int i = 0; i < nFeatures; i++)
            {
                List<Double> listAux = means.get(k);
                listAux.set(i, listAux.get(i) / groupsMap.get(k).doubleValue());
            }
        }

        variance = new HashMap<>();

        for (Penguin p : penguins)
        {
            List<Number> numericValues = p.getNumericValues();
            T group = target.apply(p);

            for (int i = 0; i < nFeatures; i++)
            {
                if (!variance.keySet().contains(group))
                    variance.put(group, new ArrayList<>(Collections.nCopies(nFeatures, 0.0)));

                List<Double> listAux = variance.get(group);
                double squared =
                        Math.pow(numericValues.get(i).doubleValue() - means.get(group).get(i), 2);
                listAux.set(i, listAux.get(i) + squared);
            }
        }

        for (T k : variance.keySet())
        {
            for (int i = 0; i < nFeatures; i++)
            {
                List<Double> listAux = variance.get(k);
                listAux.set(i, listAux.get(i) / (groupsMap.get(k).doubleValue() - 1));
            }
        }

    }

    private double ProbDensFunc(double u, double v, double x)
    {
        double left = 1.0 / Math.sqrt(2 * Math.PI * v);
        double right = Math.exp(-(x - u) * (x - u) / (2 * v));
        return left * right;
    }

    public List<PredictedPenguin<T>> predict(List<Penguin> testData)
    {

        List<PredictedPenguin<T>> predictions = new ArrayList<>();

        Integer nFeatures = penguins.get(0).getNumericValues().size();

        for (Penguin p : testData)
        {

            Map<T, List<Double>> condProbs = new HashMap<>();
            List<Number> unk = p.getNumericValues();
            Map<T, Double> evidence = new HashMap<>();

            for (T k : groupsMap.keySet())
            {
                evidence.put(k, groupsProb.get(k));
                condProbs.put(k, new ArrayList<>(Collections.nCopies(nFeatures, 0.0)));

                for (int i = 0; i < nFeatures; i++)
                {

                    double u = means.get(k).get(i);
                    double v = variance.get(k).get(i);
                    double x = unk.get(i).doubleValue();
                    condProbs.get(k).set(i, ProbDensFunc(u, v, x));
                    evidence.replace(k, evidence.get(k) * condProbs.get(k).get(i));
                }

            }

            double sumEvidence = evidence
            .values()
            .stream()
            .reduce(0.0, Double::sum);

            Map<T, Double> predictProbs = new HashMap<>();

            for (T k : groupsMap.keySet())
                predictProbs.put(k, evidence.get(k)/sumEvidence);            

            T prediction = predictProbs
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .get();

            predictions.add(new PredictedPenguin<T>(p, prediction));

        }

        return predictions;
    }

   

}
