package com.assignment.service;

import com.assignment.model.Author;
import com.assignment.model.Book;
import com.assignment.repository.AuthorRepository;
import com.assignment.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooksWithAuthors() {
        // Arrange
        Author author = new Author("Author", "author@email.com");
        Book book = new Book("Title", "ISBN", author);
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(Arrays.asList(book));

        // Act
        List<Book> result = libraryService.getAllBooksWithAuthors();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Title");
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    @Test
    public void testSaveBook() {
        // Arrange
        Author author = new Author("Author", "author@email.com");
        Book book = new Book("Title", "ISBN", author);
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Book savedBook = libraryService.saveBook(book);

        // Assert
        assertThat(savedBook.getTitle()).isEqualTo("Title");
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testGetBookById() {
        // Arrange
        Author author = new Author("Author", "author@email.com");
        Book book = new Book("Title", "ISBN", author);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        Book foundBook = libraryService.getBookById(1L);

        // Assert
        assertThat(foundBook.getId()).isEqualTo(1L);
        verify(bookRepository, times(1)).findById(1L);
    }
}
