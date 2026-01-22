package csd230.lab1.entities;

import csd230.lab1.pojos.Book;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity @DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {
    private String author;
    private String isbn;
    public BookEntity() {}
    public BookEntity(String t, Double p, int c, String a, String isbn) {
        super(t, p, c);
        this.author = a;
        this.isbn = isbn;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String a) { this.author = a; }

    public String getisbn() { return isbn; }
    public void setisbn(String i) { this.isbn = i; }
    @Override public String toString() { return "Book{author='" + author + "', " + super.toString() + "}"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        if (!super.equals(o)) return false;
        BookEntity book = (BookEntity) o;
        return Objects.equals(author, book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }
}
