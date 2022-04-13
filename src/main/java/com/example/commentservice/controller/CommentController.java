package com.example.commentservice.controller;

import com.example.commentservice.entity.Comment;

import com.example.commentservice.model.CommentDto;
import com.example.commentservice.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/api/v1/post/{postId}/comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @PostMapping()
    public ResponseEntity<CommentDto> postComment(@Valid @RequestBody Comment comment, @PathVariable("postId") String postID) {
        log.info("Inside CreateComment of CommentController");
        return new ResponseEntity<>(commentService.postComment(comment, postID), HttpStatus.ACCEPTED);
    }
}
