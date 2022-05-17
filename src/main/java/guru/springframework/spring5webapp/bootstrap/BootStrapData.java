package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Jimenez");
        Author juan = new Author("Juan", "Perez");

        Book ddd = new Book("Domain Driven Design", "123445");
        Book noEJB = new Book("No Entity Java Book", "56756757");

        Publisher pub = new Publisher();

        pub.setName("Publisher Name");
        pub.setCity("College Station");
        pub.setState("TX");

        publisherRepository.save(pub);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        juan.getBooks().add(noEJB);
        noEJB.getAuthors().add(juan);

        ddd.setPublisher(pub);
        pub.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        authorRepository.save(juan);
        bookRepository.save(noEJB);

        noEJB.setPublisher(pub);
        pub.getBooks().add(noEJB);

        publisherRepository.save(pub);

        System.out.println("Started in bootstrap");
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + pub.getBooks().size());
    }
}
