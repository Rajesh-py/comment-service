package com.example.commentservice.controller;



import com.example.commentservice.model.CommentDto;
import com.example.commentservice.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.QueryParam;
import java.util.List;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/api/v1/post/{postId}/comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;


    @GetMapping()
    public ResponseEntity<List<CommentDto>> showCommentsByPostId(@PathVariable("postId") String postID, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        log.info("Inside ListAllComments of CommentController");
        return new ResponseEntity<>(commentService.showCommentsByPostId(postID,page,pageSize), HttpStatus.ACCEPTED);
    }



}
