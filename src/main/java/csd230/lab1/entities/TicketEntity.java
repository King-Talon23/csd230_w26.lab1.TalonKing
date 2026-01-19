package csd230.lab1.entities;

import csd230.lab1.pojos.Magazine;
import csd230.lab1.pojos.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity @DiscriminatorValue("TICKET")
public class TicketEntity extends ProductEntity {
    private String description;
    @Column(name = "ticket_price") private double price;
    public TicketEntity() {}
    public TicketEntity(String d, double p) { this.description = d; this.price = p; }

    @Override public void sellItem() { System.out.println("Selling Ticket: " + description + " for $" + price); }
    @Override public double getPrice() { return price; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public void setPrice(double p) { this.price = p; }
    @Override public String toString() { return "Ticket{desc='" + description + "', price=" + price + "}"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        if (!super.equals(o)) return false;
        Ticket t = (Ticket) o;
        return Objects.equals(price, t.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }
}
