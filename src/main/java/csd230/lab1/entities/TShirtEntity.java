package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TSHIRT")
public class TShirtEntity extends ClothingItemEntity {

    @Column(nullable = true)
    private String sleeveLength;

    public TShirtEntity() {}

    public TShirtEntity(String size, Double price, int copies, String sleeveLength) {
        super(size, price, copies);
        this.sleeveLength = sleeveLength;
    }

    public String getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(String sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    @Override
    public String toString() {
        return "TShirtEntity{" +
                "id=" + getId() +
                ", size='" + size + '\'' +
                ", price=" + this.getPrice() +
                ", sleeveLength='" + sleeveLength + '\'' +
                '}';
    }
}
