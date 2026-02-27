package csd230.lab1.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public abstract class PublicationEntity extends ProductEntity {
    private String title;
    private Integer copies; // int → Integer
    public PublicationEntity() {}
    public PublicationEntity(String t, Double p, Integer c) {
        super(p);
        this.title = t;
        this.copies = c; }

    @Override public void sellItem() {
        if (copies > 0) { copies--; System.out.println("Sold '" + title + "'. Remaining copies: " + copies); }
        else { System.out.println("Cannot sell '" + title + "'. Out of stock."); }
    }
    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }
    public Integer getCopies() { return copies; }
    public void setCopies(Integer c) { this.copies = c; }
    @Override public String toString() { return "Pub{title='" + title + "', price=" + this.getPrice() + ", copies=" + copies + "}"; }
}