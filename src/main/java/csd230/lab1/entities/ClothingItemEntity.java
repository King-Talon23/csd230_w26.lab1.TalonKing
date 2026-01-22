package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public abstract class ClothingItemEntity extends ProductEntity {

    @Column(nullable = true)
    protected String size;

    @Column(nullable = false)
    protected int copies;

    public ClothingItemEntity() {}

    public ClothingItemEntity(String size, Double price, int copies) {
        super(price);
        this.size = size;
        this.copies = copies;
    }

    @Override
    public void sellItem() {
        System.out.println(this.toString() + "has been sold");
        if (copies > 0) {
            copies--;
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }


    @Override
    public String toString() {
        return "ClothingItemEntity{" +
                "id=" + getId() +
                ", size='" + size + '\'' +
                ", price=" + this.getPrice() +
                '}';
    }
}
