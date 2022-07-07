package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.model.User;
import com.example.commentservice.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @MockBean
    private CommentRepository commentRepo;

    Comment DTO1 = new Comment("123","6304","rajesh","good morning", new Date(),new Date());
    Comment DTO2 = new Comment("456","6305","babu","good afternoon", new Date(),new Date());
    Comment DTO3 = new Comment("789","6306","srikar","good evening", new Date(),new Date());


    @Test
    void postComment() {
        Comment comment=create();
        Mockito.when(commentRepo.save(comment)).thenReturn(comment);
        assertEquals(comment,commentRepo.save(comment));


    }

    @Test
    void showCommentsByPostId() {
        List<Comment> comments=createCommentList();
        Mockito.when(commentRepo.findByPostID("6304")).thenReturn(comments);
        assertEquals(0,commentRepo.findAll().size());

    }

    @Test
    void findByCommentId() {
        CommentDto commentDto = getOneComment();
        Optional<Comment> comment = getComment();
        Mockito.when(commentRepo.findById(commentDto.getCommentId())).thenReturn(comment);
        assertEquals(comment,commentRepo.findById(commentDto.getCommentId()));
    }

    @Test
    void updateComment() {
        Optional<Comment> comment = getComment();
        Mockito.when(commentRepo.findById(comment.get().getCommentId())).thenReturn(comment);
        assertEquals(comment,commentRepo.findById(comment.get().getCommentId() ));
    }

    @Test
    void deletebyCommentId() {
        Comment  comment = create();
        Mockito.when(commentRepo.findById(comment.getCommentId())).thenReturn(Optional.of(comment));
        commentRepo.delete(comment);
        verify(commentRepo,times(1)).delete(comment);

    }

    @Test
    void commentCount() {
    }

    private Comment create() {
        Comment comment = new Comment();
        comment.setCommentId("123");
        comment.setPostID("6304");
        comment.setCommentedBy("rajesh");
        comment.setComment("good morning");
        comment.setCreatedAt(new Date(2022,04,12));
        comment.setUpdatedAt(new Date(2022,04,8));

        return comment;
    }
    private CommentDto getOneComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId("123");
        commentDto.setCommentedBy(new User());
        commentDto.setComment("good morning");
        commentDto.setCreatedAt(new Date(2022,04,12));
        commentDto.setUpdatedAt(new Date(2022,04,8));
        commentDto.setLikesCount(12);
        return commentDto;
    }
    private Optional<Comment> getComment() {
        Optional<Comment> comment = Optional.of(new Comment("123",
                "6304",
                "rajesh",
                "good morning", new Date(2022, 12, 20),
                new Date(2022, 12, 20)));


        return comment;
    }
    private List<Comment> createCommentList() {
        List<Comment> comments = new ArrayList<>();

        comments.add(DTO1);
        comments.add(DTO2);
        comments.add(DTO3);
        return comments;
    }
}