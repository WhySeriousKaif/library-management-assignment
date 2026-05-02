package com.assignment.repository;

import com.assignment.model.Author;
import com.assignment.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFindAllBooksWithAuthors() {
        // Arrange
        Author author = new Author("Test Author", "test@example.com");
        authorRepository.save(author);

        Book book = new Book("Test Book", "123456789", author);
        bookRepository.save(book);

        // Act
        List<Book> books = bookRepository.findAllBooksWithAuthors();

        // Assert
        assertThat(books).isNotEmpty();
        Book foundBook = books.stream().filter(b -> b.getTitle().equals("Test Book")).findFirst().orElse(null);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getAuthor()).isNotNull();
        assertThat(foundBook.getAuthor().getName()).isEqualTo("Test Author");
    }
}
