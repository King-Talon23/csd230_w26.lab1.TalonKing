package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("JACKET")
public class JacketEntity extends ClothingItemEntity {

    @Column(nullable = true)
    private boolean insulated;

    public JacketEntity() {}

    public JacketEntity(String size, Double price, int copies, boolean insulated) {
        super(size, price, copies);
        this.insulated = insulated;
    }

    public boolean isInsulated() {
        return insulated;
    }

    public void setInsulated(boolean insulated) {
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
