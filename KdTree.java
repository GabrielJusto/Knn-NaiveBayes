import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KdTree<T> {

    private KdNode root;
    private List<Penguin> penguins;
    private Function<Penguin, T> target;

    public KdTree(List<Penguin> penguins, Function<Penguin, T> target) {
        this.target = target;
        this.penguins = penguins;
        this.root = null;

        buildTree();
    }

    public List<PredictedPenguin<T>> predict(List<Penguin> testData) {

        List<PredictedPenguin<T>> predictions = new ArrayList<>();

        for (Penguin test : testData) {

            KdNode prediction = predict(0, test.getNumericValues().size(), root, root, test);
            predictions.add(new PredictedPenguin<T>(test, target.apply(prediction.getPenguin())));
        }

        return predictions;

    }

    private KdNode predict(int feature, int nFeatures, KdNode node, KdNode best, Penguin penguin) {
        if (node == null)
            return best;

        double targetValue = penguin.getNumericValues().get(feature).doubleValue();
        double currentValue = node.getPenguin().getNumericValues().get(feature).doubleValue();
        double bestValue = best.getPenguin().getNumericValues().get(feature).doubleValue();
        KdNode newBest;
        KdNode badSide;
        KdNode currentBest;
        if (Math.pow(targetValue - currentValue, 2) < Math.pow(targetValue - bestValue, 2))
            currentBest = node;
        else
            currentBest = best;

        if (targetValue < currentValue) {
            newBest = predict((feature + 1) % nFeatures, nFeatures, node.getLeft(), currentBest, penguin);
            badSide = node.getRight();
        } else {
            newBest = predict((feature + 1) % nFeatures, nFeatures, node.getRight(), currentBest, penguin);
            badSide = node.getLeft();
        }

        double newBestValue = newBest.getPenguin().getNumericValues().get(feature).doubleValue();
        if (Math.pow(targetValue - currentValue, 2) < Math.pow(targetValue - newBestValue, 2))
            newBest = predict((feature + 1) % nFeatures, nFeatures, badSide, currentBest, penguin);

        return newBest;
    }

    public void buildTree() {
        this.root = buildTree(0, penguins.get(0).getNumericValues().size(), penguins);
    }

    private KdNode buildTree(int feature, int nFeature, List<Penguin> listPenguins) {
        if (listPenguins == null || listPenguins.size() == 0)
            return null;

        List<PenguinFeature> listFeature = listPenguins.stream()
                .map(p -> new PenguinFeature(p, p.getNumericValues().get(feature)))
                .sorted()
                .collect(Collectors.toList());

        int middle = listFeature.size() / 2;
        KdNode node = new KdNode(listFeature.get(middle).getPenguin());

        List<Penguin> leftPenguins = listFeature
                .subList(0, middle)
                .stream()
                .map(PenguinFeature::getPenguin)
                .collect(Collectors.toList());

        List<Penguin> rightPenguins = listFeature
                .subList(middle + 1, listFeature.size())
                .stream()
                .map(PenguinFeature::getPenguin)
                .collect(Collectors.toList());

        node.setLeft(buildTree((feature + 1) % nFeature, nFeature, leftPenguins));
        node.setRight(buildTree((feature + 1) % nFeature, nFeature, rightPenguins));

        return node;
    }

    public KdNode getRoot() {
        return root;
    }
}
