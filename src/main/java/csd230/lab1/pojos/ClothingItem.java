package csd230.lab1.pojos;

public abstract class ClothingItem extends Product {

    protected String size;
    protected double price;
    protected int copies;

    public ClothingItem() {
    }

    public ClothingItem(String productId, String size, double price, int copies) {
        setProductId(productId);
        this.size = size;
        this.price = price;
        this.copies = copies;
    }

    @Override
    public void initialize() {
        System.out.println("Enter size:");
        this.size = getInput(this.size);

        System.out.println("Enter price:");
        this.price = getInput(this.price);

        System.out.println("Enter copies:");
        this.copies = getInput(this.copies);
    }

    @Override
    public void edit() {
        System.out.println("Edit size [" + size + "]:");
        this.size = getInput(this.size);

        System.out.println("Edit price [" + price + "]:");
        this.price = getInput(this.price);

        System.out.println("Edit copies [" + copies + "]:");
        this.copies = getInput(this.copies);
    }

    @Override
    public void sellItem() {
        if (copies > 0) {
            copies--;
        }
    }

    @Override
    public Double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "ClothingItem{productId=" + getProductId() +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }
}
