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
import javax.ws.rs.QueryParam;
import java.util.List;

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

    @GetMapping()
    public ResponseEntity< List<CommentDto>> showCommentsByPostId(@PathVariable("postId") String postID, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        log.info("Inside ListAllComments of CommentController");
        return new ResponseEntity<>(commentService.showCommentsByPostId(postID,page,pageSize), HttpStatus.ACCEPTED);
    }



    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> findByCommentId(@PathVariable("postId") String postID, @PathVariable("commentId") String commentId) {
        log.info("Inside CommentByID of CommentController");
        return new ResponseEntity<>(commentService.findByCommentId(commentId), HttpStatus.ACCEPTED);
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody @Valid Comment comment, @PathVariable("postId") String postID, @PathVariable("commentId") String commentId) {
        log.info("Inside UpdateComment of CommentController");
        return new ResponseEntity<>(commentService.updateComment(comment, postID, commentId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deletebyCommentId(@PathVariable("postId") String postID,@PathVariable("commentId") String commentId) {
        log.info("Inside DeleteComment of CommentController");
        return new ResponseEntity<>(commentService.deletebyCommentId(commentId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> commentCount(@PathVariable("postId") String postID) {
        log.info("Inside CommentCount of CommentController");
        return new ResponseEntity<>(commentService.commentCount(postID), HttpStatus.ACCEPTED);
    }

}
