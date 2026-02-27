package csd230.lab1.entities;

import csd230.lab1.pojos.Magazine;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity @DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {
    private Integer orderQty; // int → Integer
    private LocalDateTime currentIssue;

    public MagazineEntity() {
    }

    public MagazineEntity(String t, Double p, Integer c, Integer o, LocalDateTime d) {
        super(t, p, c);
        this.orderQty = o;
        this.currentIssue = d;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer o) {
        this.orderQty = o;
    }

    public void setCurrentIssue(LocalDateTime d) {
        this.currentIssue = d;
    }

    public LocalDateTime getCurrentIssue() {
        return currentIssue;
    }

    @Override
    public String toString() {
        return "Mag{issue=" + currentIssue + ", " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagazineEntity)) return false;
        if (!super.equals(o)) return false;
        MagazineEntity mag = (MagazineEntity) o;
        return Objects.equals(currentIssue, mag.getCurrentIssue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentIssue);
    }
}