public class PenguinFeature implements Comparable<PenguinFeature>
{
    
    private final Penguin penguin;
    private final Number feature;


    public PenguinFeature(Penguin penguin, Number feature) {
        this.penguin = penguin;
        this.feature = feature;
    }



    public int compareTo(PenguinFeature other)
    {
        return (int) (feature.intValue() - other.feature.intValue());   
    }





    public Penguin getPenguin() {
        return this.penguin;
    }
}
