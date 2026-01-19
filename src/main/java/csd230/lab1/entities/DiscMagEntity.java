package csd230.lab1.entities;

import csd230.lab1.pojos.Book;
import csd230.lab1.pojos.DiscMag;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity @DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {
    private boolean hasDisc;
    public DiscMagEntity() {}
    public DiscMagEntity(String t, double p, int c, int o, LocalDateTime d, boolean h) { super(t, p, c, o, d); this.hasDisc = h; }
    public boolean isHasDisc() { return hasDisc; }
    public void setHasDisc(boolean h) { this.hasDisc = h; }
    @Override public String toString() { return "DiscMag{disc=" + hasDisc + ", " + super.toString() + "}"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscMag)) return false;
        if (!super.equals(o)) return false;
        DiscMag discMag = (DiscMag) o;
        return Objects.equals(hasDisc, discMag.isHasDisc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }}
