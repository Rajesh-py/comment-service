package com.example.commentservice.controller;



import com.example.commentservice.entity.Comment;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.model.User;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(CommentController.class)
class CommentControllerTest {


    @MockBean
    CommentService commentService;

    @MockBean
    CommentController commentController;

    @MockBean
    CommentRepository commentRepository;


    @Autowired
    MockMvc mockMvc;

    CommentDto DTO1 = new CommentDto("123",new User(),"good morning", new Date(),new Date(),12);
    CommentDto DTO2 = new CommentDto("456",new User(),"good afternoon", new Date(),new Date(),13);
    CommentDto DTO3 = new CommentDto("789",new User(),"good evening", new Date(),new Date(),14);


    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void postComment() throws Exception {
        Comment comment = create();
        CommentDto commentDto = getOneComment();
        Mockito.when(commentService.postComment(comment,"6304")).thenReturn(commentDto);
        mockMvc.perform(post("/api/v1/post/postId/comments")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void showCommentsByPostId() throws Exception {
        CommentDto commentDto = getOneComment();
        List<CommentDto> commentDto1 = createCommentList();
        Mockito.when(commentService.showCommentsByPostId("6304", 1,2)).thenReturn(commentDto1);
        mockMvc.perform(get("/api/v1/post/postId/comments")
                        .content(asJsonString(commentDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void findByCommentId() throws Exception {
        CommentDto commentDto = getOneComment();
        Mockito.when(commentService.findByCommentId(getOneComment().getCommentId())).thenReturn(commentDto);
        mockMvc.perform(get("/api/v1/post/postId/comments/commentId")
                        .content(asJsonString(commentDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateComment() throws Exception {
        Comment comment= create();
        CommentDto commentDto= getOneComment();

        Mockito.when(commentService.updateComment(comment,comment.getPostID(),comment.getCommentId())).thenReturn(commentDto);
        mockMvc.perform(put("/api/v1/post/postId/comments/commentId")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletebyCommentId() throws Exception {
        CommentDto commentDto = getOneComment();
        Mockito.when(commentService.deletebyCommentId(commentDto.getCommentId())).thenReturn(String.valueOf(true));
        mockMvc.perform(delete("/api/v1/post/postId/comments/commentId")
                        .content(asJsonString(commentDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void commentCount() throws Exception {
        List<CommentDto> comment1 = createCommentList();
        Mockito.when(commentService.commentCount("6304")).thenReturn(3);
        mockMvc.perform(get("/api/v1/post/postId/comments/count")
                        .content(asJsonString(comment1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

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