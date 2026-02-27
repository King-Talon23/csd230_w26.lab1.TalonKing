package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("JACKET")
public class JacketEntity extends ClothingItemEntity {

    @Column(nullable = true)
    private Boolean insulated; // boolean → Boolean

    public JacketEntity() {}

    public JacketEntity(String size, Double price, Integer copies, Boolean insulated) {
        super(size, price, copies);
        this.insulated = insulated;
    }

    public Boolean isInsulated() {
        return insulated;
    }

    public void setInsulated(Boolean insulated) {
        this.insulated = insulated;
    }

    @Override
    public String toString() {
        return "JacketEntity{" +
                "id=" + getId() +
                ", size='" + size + '\'' +
                ", price=" + this.getPrice() +
                ", insulated=" + insulated +
                '}';
    }
}