package csd230.lab1.pojos;

public class Jacket extends ClothingItem {

    private boolean insulated;

    public Jacket() {
    }

    public Jacket(String productId, String size, double price, int copies, boolean insulated) {
        super(productId, size, price, copies);
        this.insulated = insulated;
    }

    @Override
    public void initialize() {
        super.initialize();

        System.out.println("Is the jacket insulated? (true/false):");
        this.insulated = getInput(this.insulated);
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println("Edit insulated [" + insulated + "]:");
        this.insulated = getInput(this.insulated);
    }

    public boolean isInsulated() {
        return insulated;
    }

    public void setInsulated(boolean insulated) {
        this.insulated = insulated;
    }

    @Override
    public String toString() {
        return "Jacket{" +
                "insulated=" + insulated +
                ", " + super.toString() +
                '}';
    }
}
