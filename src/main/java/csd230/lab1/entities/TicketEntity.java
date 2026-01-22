package csd230.lab1.entities;

import csd230.lab1.pojos.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity @DiscriminatorValue("TICKET")
public class TicketEntity extends ProductEntity {
    @Column(nullable = true)
    private String description;
    public TicketEntity() {}
    public TicketEntity(String d, Double p) {
        super(p);
    this.description = d;  }

    @Override public void sellItem() { System.out.println("Selling Ticket: " + description + " for $" + this.getPrice()); }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    @Override public String toString() { return "Ticket{desc='" + description + "', price=" + this.getPrice() + "}"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity)) return false;
        if (!super.equals(o)) return false;
        TicketEntity t = (TicketEntity) o;
        return Objects.equals(this.getPrice(), t.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }
}
