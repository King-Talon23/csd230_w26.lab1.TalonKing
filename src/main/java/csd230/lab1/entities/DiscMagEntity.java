package csd230.lab1.entities;

import csd230.lab1.pojos.Book;
import csd230.lab1.pojos.DiscMag;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {

    @Column(nullable = true)
    private Boolean hasDisc; // boolean → Boolean

    public DiscMagEntity() {
    }

    public DiscMagEntity(String t, Double p, Integer c, Integer o, LocalDateTime d, Boolean h) {
        super(t, p, c, o, d);
        this.hasDisc = h;
    }

    public Boolean isHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(Boolean h) {
        this.hasDisc = h;
    }

    @Override
    public String toString() {
        return "DiscMag{disc=" + hasDisc + ", " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscMagEntity)) return false;
        if (!super.equals(o)) return false;
        DiscMagEntity discMag = (DiscMagEntity) o;
        return Objects.equals(hasDisc, discMag.isHasDisc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }
}