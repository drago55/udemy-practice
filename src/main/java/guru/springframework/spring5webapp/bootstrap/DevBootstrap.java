package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.publisherRepository = publisherRepository;
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	private void intData() {
		Publisher publisher1 = new Publisher("foo", "some addrres");
		
		publisherRepository.save(publisher1);
		
		Author eric = new Author("Eric" ,"Evans");
		Book ddd =  new Book("Domain driven desing", "1234", publisher1);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		Author rod = new Author("Rod" ,"Johanson");
				
		Book jve2 =  new Book("J2EE development without ", "22345", publisher1);
		eric.getBooks().add(jve2);
		ddd.getAuthors().add(rod);
		authorRepository.save(rod);
		bookRepository.save(jve2);
		
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		intData();
	}

}
