package com.example.commentservice.repository;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
class CommentRepositoryTest {


    @Autowired
    CommentRepository commentRepository;


    @BeforeEach
    void initUseCase() {
        Comment comment = create();
        commentRepository.save(comment);
    }

    @AfterEach
    public void destroyByAll() {
        commentRepository.deleteAll();
    }

    CommentDto DTO1 = new CommentDto("123",new User(),"good morning", new Date(),new Date(),12);
    CommentDto DTO2 = new CommentDto("456",new User(),"good afternoon", new Date(),new Date(),13);
    CommentDto DTO3 = new CommentDto("789",new User(),"good evening", new Date(),new Date(),14);


    @Test
    void findByPostID() {
        List<Comment> comment = commentRepository.findByPostID(String.valueOf(createCommentList().getClass()));
        assertEquals("6304", comment.getClass());
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
    private List<CommentDto> createCommentList() {
        List<CommentDto> comments = new ArrayList<>();

        comments.add(DTO1);
        comments.add(DTO2);
        comments.add(DTO3);
        return comments;
    }
}