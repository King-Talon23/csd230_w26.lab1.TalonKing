package csd230.lab1.entities;

import csd230.lab1.pojos.Book;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity @DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {
    private String author;
    public BookEntity() {}
    public BookEntity(String t, double p, int c, String a) { super(t, p, c); this.author = a; }
    public String getAuthor() { return author; }
    public void setAuthor(String a) { this.author = a; }
    @Override public String toString() { return "Book{author='" + author + "', " + super.toString() + "}"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }
}
