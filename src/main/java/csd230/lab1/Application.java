package csd230.lab1;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CartRepository cartRepository;
	private final BookRepository bookRepository;
	private final MagazineRepository magazineRepository;
	private final TicketRepository ticketRepository;
	private final DiscMagRepository discMagRepository;
	private final JacketRepository jacketRepository;
	private final TShirtRepository tShirtRepository;

	public Application(
			ProductRepository productRepository,
			CartRepository cartRepository,
			BookRepository bookRepository,
			MagazineRepository magazineRepository,
			TicketRepository ticketRepository,
			DiscMagRepository discMagRepository,
			JacketRepository jacketRepository,
			TShirtRepository tShirtRepository
	) {
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
		this.bookRepository = bookRepository;
		this.magazineRepository = magazineRepository;
		this.ticketRepository = ticketRepository;
		this.discMagRepository = discMagRepository;
		this.jacketRepository = jacketRepository;
		this.tShirtRepository = tShirtRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) {

		Faker faker = new Faker();
		Commerce commerce = faker.commerce();

		BookEntity book = new BookEntity(
				faker.book().title(),
				4.61,
				10,
				faker.book().author(),
				"1234-5678-9012-3456"
		);
		book.setPrice(1.82);
		bookRepository.save(book);

		MagazineEntity magazine = new MagazineEntity(
				faker.lorem().word() + " Magazine",
				12.99,
				20,
				50,
				LocalDateTime.now()
		);
		magazineRepository.save(magazine);

		TicketEntity ticket = new TicketEntity(
				faker.company().name() + " Event",
				5.34
		);

		ticketRepository.save(ticket);
		System.out.println("Saved Ticket: " + ticket);

		DiscMagEntity discMag = new DiscMagEntity(
				faker.music().genre() + " Monthly",
				8.92,
				50,
				faker.number().numberBetween(1, 12),
				LocalDateTime.now(),
				true
		);

		discMagRepository.save(discMag);
		System.out.println("Saved DiscMag: " + discMag);


		JacketEntity jacket = new JacketEntity(
				"L",
				89.99,
				5,
				true		);
		jacketRepository.save(jacket);

		TShirtEntity tShirt = new TShirtEntity(
				"M",
				24.99,
				15,
				"Short Sleeve"
		);
		tShirtRepository.save(tShirt);

		CartEntity cart = new CartEntity();
		cartRepository.save(cart);

		cart.addProduct(book);
		cart.addProduct(magazine);
		cart.addProduct(jacket);
		cart.addProduct(tShirt);
		cartRepository.save(cart);

		System.out.println("LISTING ALL PRODUCTS");
		List<ProductEntity> allProducts = productRepository.findAll();
		for (ProductEntity p : allProducts) {
			System.out.println(p);
		}

		System.out.println("\nSHOWING ALL CATRS");
		List<CartEntity> allCarts = cartRepository.findAll();
		for (CartEntity c : allCarts) {
			System.out.println(c);
			for (ProductEntity p : c.getProducts()) {
				System.out.println("  -> " + p);
			}
		}


		book.setPrice(book.getPrice() + 5.00);
		bookRepository.save(book);

		jacket.sellItem();
		jacketRepository.delete(jacket);

		tShirt.sellItem();
		tShirtRepository.delete(tShirt);

		System.out.println("\nSHOWIGN UPDATES/DELETE");
		productRepository.findAll().forEach(System.out::println);
	}
}
