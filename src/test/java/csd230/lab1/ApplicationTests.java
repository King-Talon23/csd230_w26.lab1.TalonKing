package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import com.github.javafaker.Faker;
import com.github.javafaker.Commerce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ApplicationTests {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private DiscMagRepository discMagRepository;

	@Autowired
	private JacketRepository jacketRepository;

	@Autowired
	private TShirtRepository tShirtRepository;

	@Autowired
	private CartRepository cartRepository;

	private final Faker faker = new Faker();
	private final Commerce cm = faker.commerce();
	@Test
	void testBookCRUD() {
		BookEntity book = new BookEntity();
		book.setTitle(cm.productName());
		book.setAuthor(faker.book().author());
		book.setPrice(1.28);
		book.setCopies(faker.number().numberBetween(1, 50));

		bookRepository.save(book);
		Long id = book.getId();

		BookEntity retrieved = bookRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId());

		retrieved.setPrice(retrieved.getPrice() + 5.0);
		bookRepository.save(retrieved);
		assertEquals(retrieved.getPrice(), bookRepository.findById(id).get().getPrice());

		bookRepository.delete(retrieved);
		assertTrue(bookRepository.findById(id).isEmpty());
	}

	@Test
	void testMagazineCRUD() {
		MagazineEntity magazine = new MagazineEntity();
		magazine.setOrderQty(faker.number().numberBetween(1, 500));
		magazine.setPrice(faker.number().randomDouble(2, 5, 30));

		magazineRepository.save(magazine);
		Long id = magazine.getId();
		assertNotNull(id);

		MagazineEntity retrieved = magazineRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId());

		retrieved.setPrice(retrieved.getPrice() + 3.0);
		magazineRepository.save(retrieved);
		assertEquals(
				retrieved.getPrice(),
				magazineRepository.findById(id).get().getPrice()
		);

		magazineRepository.delete(retrieved);
		assertTrue(magazineRepository.findById(id).isEmpty());
	}


	@Test
	void testDiscMagCRUD() {
		DiscMagEntity discMag = new DiscMagEntity();
		discMag.setHasDisc(false);
		discMag.setPrice(3.99);
		discMag.setPrice(faker.number().randomDouble(2, 5, 50));

		discMagRepository.save(discMag);
		Long id = discMag.getId();

		DiscMagEntity retrieved = discMagRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId());

		retrieved.setPrice(retrieved.getPrice() + 2.5);
		discMagRepository.save(retrieved);
		assertEquals(retrieved.getPrice(), discMagRepository.findById(id).get().getPrice());

		discMagRepository.delete(retrieved);
		assertTrue(discMagRepository.findById(id).isEmpty());
	}

	@Test
	void testTicketCRUD() {
		TicketEntity ticket = new TicketEntity();
		ticket.setDescription(faker.music().genre());
		ticket.setPrice(faker.number().randomDouble(2, 25, 200));

		ticketRepository.save(ticket);
		Long id = ticket.getId();
		assertNotNull(id);

		TicketEntity retrieved = ticketRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId());

		retrieved.setPrice(retrieved.getPrice() + 15.0);
		ticketRepository.save(retrieved);
		assertEquals(
				retrieved.getPrice(),
				ticketRepository.findById(id).get().getPrice()
		);

		ticketRepository.delete(retrieved);
		assertTrue(ticketRepository.findById(id).isEmpty());
	}



	@Test
	void testJacketCRUD() {
		JacketEntity jacket = new JacketEntity();
		jacket.setPrice(56.22);
		jacket.setInsulated(true);
		jacket.setSize(faker.options().option("S", "M", "L", "XL"));
		jacket.setPrice(faker.number().randomDouble(2, 20, 150));

		jacketRepository.save(jacket);
		Long id = jacket.getId();

		JacketEntity retrieved = jacketRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId());

		retrieved.setPrice(retrieved.getPrice() + 10);
		jacketRepository.save(retrieved);
		assertEquals(retrieved.getPrice(), jacketRepository.findById(id).get().getPrice());

		jacketRepository.delete(retrieved);
		assertTrue(jacketRepository.findById(id).isEmpty());
	}

	@Test
	void testTShirtCRUD() {
		TShirtEntity tshirt = new TShirtEntity();
		tshirt.setSleeveLength("short");
		tshirt.setSize(faker.options().option("S", "M", "L", "XL"));
		tshirt.setPrice(faker.number().randomDouble(2, 10, 60));

		tShirtRepository.save(tshirt);
		Long id = tshirt.getId();

		TShirtEntity retrieved = tShirtRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId());

		retrieved.setPrice(retrieved.getPrice() + 5);
		tShirtRepository.save(retrieved);
		assertEquals(retrieved.getPrice(), tShirtRepository.findById(id).get().getPrice());

		tShirtRepository.delete(retrieved);
		assertTrue(tShirtRepository.findById(id).isEmpty());
	}

	@Test
	void testCart() {
		CartEntity cart = new CartEntity();

		cart = cartRepository.save(cart);
		Long id = cart.getId();
		assertNotNull(id, "Cart ID should not be null after save");

		CartEntity retrieved = cartRepository.findById(id).orElseThrow();
		assertEquals(id, retrieved.getId(), "Retrieved Cart ID should match saved Cart ID");

		cartRepository.save(retrieved);
		CartEntity updated = cartRepository.findById(id).orElseThrow();
		assertEquals(id, updated.getId(), "Updated Cart ID should still match");

		cartRepository.delete(updated);
		assertTrue(cartRepository.findById(id).isEmpty(), "Cart should be deleted");
	}

}
