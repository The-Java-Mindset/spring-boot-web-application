package com.thejavamindset.library.repository;

import com.thejavamindset.library.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.Optional;

@SpringBootTest
public class BookRepositoryTest {

    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testCreateBook() {
        Book newBook = new Book();
        newBook.setTitle("MyBook");
        newBook.setAuthor("The Java Mindset");
        newBook.setIsbn("0123456789123");

        Book persistedBook = bookRepository.save(newBook);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(persistedBook.getUuid(), "UUID was not generated"),
                () -> Assertions.assertTrue(bookRepository.findById(persistedBook.getUuid()).isPresent(), "Book not found")
        );
    }

    @Test
    public void testDataLoaded() {
        Book bookToSearch = new Book();
        bookToSearch.setTitle("Effective Java");

        Example<Book> bookExample = Example.of(bookToSearch);

        Optional<Book> optionalBook = bookRepository.findOne(bookExample);

        Assertions.assertTrue(optionalBook.isPresent(), "Book not found.");
        Assertions.assertEquals(optionalBook.get().getAuthor(), "Joshua Bloch", "Wrong author.");
    }
}
