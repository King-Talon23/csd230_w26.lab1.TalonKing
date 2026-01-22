package csd230.lab1.repositories;

import csd230.lab1.entities.BookEntity;
import csd230.lab1.pojos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findBookById(long id);
    List<Book> findByIsbn(String isbn);
    Book findById(long id);
    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByPrice(double price);
    List<BookEntity> findByCopies(int copies);
    List<BookEntity> findByTitleLike(String titlePattern);

    @Query("SELECT b FROM BookEntity b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<BookEntity> findByPriceRange(@Param("minPrice") Double minPrice,
                                      @Param("maxPrice") Double maxPrice);
}