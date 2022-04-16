public class PredictedPenguin <T>
{

    private Penguin penguin;
    private T predictedClass;



    public PredictedPenguin(Penguin penguin, T predictedClass) {
        this.penguin = penguin;
        this.predictedClass = predictedClass;
    }
    

    public Penguin getPenguin() {
        return this.penguin;
    }

    public T getPredictedClass() {
        return this.predictedClass;
    }


    @Override
    public String toString() {
        return "{" +
            " penguin='" + getPenguin().getSampleNumber() + "'" +
            ", predictedClass='" + getPredictedClass() + "'" +
            "}";
    }



}
