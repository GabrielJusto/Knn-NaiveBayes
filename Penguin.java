import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Penguin
{

    private String studyName;
    private Integer sampleNumber;
    private String species;
    private String region;
    private String island;
    private String stage;
    private String individualId;
    private Boolean clutchCompletion;
    private LocalDate dateEgg;
    private Float culmenLength;
    private Float culmenDepth;
    private Float flipperLength;
    private Integer bodyMass;
    private Sex sex;
    private Float delta15;
    private Float delta13;
    private String comments;

    


    public Penguin(String studyName, Integer sampleNumber, String species, String region, String island, String stage, String individualId, Boolean clutchCompletion, LocalDate dateEgg, Float culmenLength, Float culmenDepth, Float flipperLength, Integer bodyMass, Sex sex, Float delta15, Float delta13, String comments) {
        this.studyName = studyName;
        this.sampleNumber = sampleNumber;
        this.species = species;
        this.region = region;
        this.island = island;
        this.stage = stage;
        this.individualId = individualId;
        this.clutchCompletion = clutchCompletion;
        this.dateEgg = dateEgg;
        this.culmenLength = culmenLength;
        this.culmenDepth = culmenDepth;
        this.flipperLength = flipperLength;
        this.bodyMass = bodyMass;
        this.sex = sex;
        this.delta15 = delta15;
        this.delta13 = delta13;
        this.comments = comments;
    }


    public List<Number> getNumericValues()
    {
        return Arrays.asList(culmenLength, culmenDepth, flipperLength, bodyMass, delta15, delta13);
    }

    public double getDistance(Penguin other)
    {
        return Math.sqrt(
            Math.pow(this.culmenLength - other.culmenLength, 2) + 
            Math.pow(this.culmenDepth - other.culmenDepth, 2) +
            Math.pow(this.flipperLength - other.flipperLength, 2) +
            Math.pow(this.bodyMass - other.bodyMass, 2) +
            Math.pow(this.delta15 - other.delta15, 2) +
            Math.pow(this.delta13 - other.delta13, 2) );
    }

    public String getStudyName() {
        return this.studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public Integer getSampleNumber() {
        return this.sampleNumber;
    }

    public void setSampleNumber(Integer sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIsland() {
        return this.island;
    }

    public void setIsland(String island) {
        this.island = island;
    }

    public String getStage() {
        return this.stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getIndividualId() {
        return this.individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public Boolean isClutchCompletion() {
        return this.clutchCompletion;
    }

    public Boolean getClutchCompletion() {
        return this.clutchCompletion;
    }

    public void setClutchCompletion(Boolean clutchCompletion) {
        this.clutchCompletion = clutchCompletion;
    }

    public LocalDate getDateEgg() {
        return this.dateEgg;
    }

    public void setDateEgg(LocalDate dateEgg) {
        this.dateEgg = dateEgg;
    }

    public Float getCulmenLength() {
        return this.culmenLength;
    }

    public void setCulmenLength(Float culmenLength) {
        this.culmenLength = culmenLength;
    }

    public Float getCulmenDepth() {
        return this.culmenDepth;
    }

    public void setCulmenDepth(Float culmenDepth) {
        this.culmenDepth = culmenDepth;
    }

    public Float getFlipperLength() {
        return this.flipperLength;
    }

    public void setFlipperLength(Float flipperLength) {
        this.flipperLength = flipperLength;
    }

    public Integer getBodyMass() {
        return this.bodyMass;
    }

    public void setBodyMass(Integer bodyMass) {
        this.bodyMass = bodyMass;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Float getDelta15() {
        return this.delta15;
    }

    public void setDelta15(Float delta15) {
        this.delta15 = delta15;
    }

    public Float getDelta13() {
        return this.delta13;
    }

    public void setDelta13(Float delta13) {
        this.delta13 = delta13;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    


    @Override
    public String toString() {
        return "{" +
            " studyName='" + getStudyName() + "'" +
            ", sampleNumber='" + getSampleNumber() + "'" +
            ", species='" + getSpecies() + "'" +
            ", region='" + getRegion() + "'" +
            ", island='" + getIsland() + "'" +
            ", stage='" + getStage() + "'" +
            ", individualId='" + getIndividualId() + "'" +
            ", clutchCompletion='" + isClutchCompletion() + "'" +
            ", dateEgg='" + getDateEgg() + "'" +
            ", culmenLength='" + getCulmenLength() + "'" +
            ", culmenDepth='" + getCulmenDepth() + "'" +
            ", flipperLength='" + getFlipperLength() + "'" +
            ", bodyMass='" + getBodyMass() + "'" +
            ", sex='" + getSex() + "'" +
            ", delta15='" + getDelta15() + "'" +
            ", delta13='" + getDelta13() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }



    

}
