package ru.otus.homework.service.impl;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.dao.CommentRepository;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {
    private static final long ID = 1;
    private static final String TEXT = "TEXT";
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetById() {
        final Comment expectedComment = new Comment(ID, TEXT, mock(Book.class));
        when(commentRepository.findById(eq(ID))).thenReturn(Optional.of(expectedComment));

        final Optional<Comment> commentOptional = commentService.getById(ID);

        assertThat(commentOptional).isNotEmpty();
        assertThat(commentOptional.get()).usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }

    @Test
    public void shouldDeleteById() {
        doNothing().when(commentRepository).deleteById(eq(ID));

        commentRepository.deleteById(ID);

        verify(commentRepository, times(1)).deleteById(ID);
    }

    @Test
    public void shouldGetAllByBookId() {
        List<Comment> expectedComments = new EasyRandom()
                .objects(Comment.class, 2)
                .collect(Collectors.toList());
        when(commentRepository.findByBook_Id(ID)).thenReturn(expectedComments);

        List<Comment> comments = commentRepository.findByBook_Id(ID);

        assertThat(comments).hasSameElementsAs(expectedComments);
    }

    @Test
    public void shouldCreateComment() {
        final Comment comment = new Comment(0, TEXT, mock(Book.class));
        when(commentRepository.save(comment)).thenReturn(comment);

        commentService.create(comment);

        verify(commentRepository, times(1)).save(comment);

    }

    @Test
    public void shouldUpdateBook() {
        final Comment expectedComment = new Comment(ID, TEXT, mock(Book.class));
        when(commentRepository.save(eq(expectedComment))).thenReturn(expectedComment);

        Comment comment = commentService.update(expectedComment);

        verify(commentRepository, times(1)).save(comment);
    }
}