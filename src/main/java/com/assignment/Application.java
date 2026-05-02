package com.assignment;

import com.assignment.model.Author;
import com.assignment.model.Book;
import com.assignment.repository.AuthorRepository;
import com.assignment.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(AuthorRepository authorRepository, BookRepository bookRepository) {
        return (args) -> {
            if (authorRepository.count() == 0) {
                Author author1 = new Author("George Orwell", "orwell@example.com");
                Author author2 = new Author("J.K. Rowling", "rowling@example.com");
                Author author3 = new Author("J.R.R. Tolkien", "tolkien@example.com");
                Author author4 = new Author("Agatha Christie", "christie@example.com");
                Author author5 = new Author("Stephen King", "king@example.com");
                
                authorRepository.save(author1);
                authorRepository.save(author2);
                authorRepository.save(author3);
                authorRepository.save(author4);
                authorRepository.save(author5);

                bookRepository.save(new Book("1984", "978-0451524935", author1));
                bookRepository.save(new Book("Animal Farm", "978-0451526342", author1));
                bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "978-0590353427", author2));
                bookRepository.save(new Book("Harry Potter and the Chamber of Secrets", "978-0439064873", author2));
                bookRepository.save(new Book("The Hobbit", "978-0547928227", author3));
                bookRepository.save(new Book("The Fellowship of the Ring", "978-0544003415", author3));
                bookRepository.save(new Book("Murder on the Orient Express", "978-0062073495", author4));
                bookRepository.save(new Book("And Then There Were None", "978-0062073488", author4));
                bookRepository.save(new Book("The Shining", "978-0307743657", author5));
                bookRepository.save(new Book("It", "978-1501142970", author5));
            }
        };
    }
}
