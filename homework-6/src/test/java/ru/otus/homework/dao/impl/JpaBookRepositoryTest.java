package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaBookRepository.class})
class JpaBookRepositoryTest {

    private static final String AUTHOR = "AUTHOR";
    private static final String GENRE = "GENRE";
    private static final String BOOK_TITLE = "BOOK_TITLE";
    @Autowired
    private JpaBookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void shouldReturnExpectedBooksCount() {
        List<Book> all = bookRepository.getAll();
        System.out.println(all);
        assertThat(all).hasSize(1);
    }

    @Test
    public void shouldCreateBook() {
        Author author = new Author(0, AUTHOR);
        Genre genre = new Genre(0, GENRE);
        Book expectedBook = new Book(0, BOOK_TITLE, author, genre);

        Book id = bookRepository.create(expectedBook);
        assertThat(id.getId()).isGreaterThan(0);

        var actualBook = em.find(Book.class, id.getId());
        System.out.println(actualBook);
        assertThat(actualBook).isNotNull()
                .matches(b -> b.getTitle().equals(BOOK_TITLE))
                .matches(b -> b.getAuthor() != null && b.getAuthor().getId() > 0)
                .matches(b -> b.getAuthor().getFullName().equals(AUTHOR))
                .matches(b -> b.getGenre() != null && b.getGenre().getId() > 0)
                .matches(b -> b.getGenre().getName().equals(GENRE));
    }

    @Test
    public void shouldFindById() {
        final Book expectedBook = em.find(Book.class, 1L);

        final Book actualBook = bookRepository.getById(1);

        assertThat(actualBook)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @Test
    public void shouldDeleteBookById() {
        bookRepository.deleteById(1L);

        final Book book = em.find(Book.class, 1L);
        assertThat(book).isNull();
    }

    @Test
    public void shouldUpdateBook() {
        final Book book = em.find(Book.class, 1L);
        em.detach(book);

        book.setTitle("Something strange");
        bookRepository.update(book);

        final Book updatedBook = em.find(Book.class, 1L);
        assertThat(updatedBook.getTitle()).isEqualTo("Something strange");
    }

}