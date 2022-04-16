public class PenguinDistance  implements Comparable<PenguinDistance>
{


    private final Penguin penguin;
    private final double distance;


    public PenguinDistance(Penguin penguin, double distance) {
        this.penguin = penguin;
        this.distance = distance;
    }



    public int compareTo(PenguinDistance other)
    {
        return (int) (distance - other.distance);   
    }





    public Penguin getPenguin() {
        return this.penguin;
    }

}
