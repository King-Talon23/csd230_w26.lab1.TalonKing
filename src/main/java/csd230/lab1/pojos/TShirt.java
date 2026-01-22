package csd230.lab1.pojos;

public class TShirt extends ClothingItem {

    private String sleeveLength;

    public TShirt() {
    }

    public TShirt(String productId, String size, double price, int copies, String sleeveLength) {
        super(productId, size, price, copies);
        this.sleeveLength = sleeveLength;
    }

    @Override
    public void initialize() {
        super.initialize();

        System.out.println("Enter sleeve length:");
        this.sleeveLength = getInput(this.sleeveLength);
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println("Edit sleeve length [" + sleeveLength + "]:");
        this.sleeveLength = getInput(this.sleeveLength);
    }

    public String getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(String sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "sleeveLength='" + sleeveLength + '\'' +
                ", " + super.toString() +
                '}';
    }
}
