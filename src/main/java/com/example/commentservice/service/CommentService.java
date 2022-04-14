package com.example.commentservice.service;


import com.example.commentservice.constants.Constants;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.exception.CommentNotFoundException;
import com.example.commentservice.feign.LikeService;
import com.example.commentservice.feign.UserService;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userFeign;

    @Autowired
    LikeService likeFeign;

    public CommentDto updateComment(Comment comment, String postID,String commentId) {
        comment.setCommentId(commentId);
        comment.setUpdatedAt(new Date());
        comment.setCreatedAt(commentRepository.findById(commentId).get().getCreatedAt());
        comment.setPostID(postID);
        commentRepository.save(comment);
        return new CommentDto(comment.getCommentId(),
                userFeign.findID(comment.getCommentedBy()),
                comment.getComment(),comment.getCreatedAt(),comment.getUpdatedAt(),
                likeFeign.countLike(comment.getCommentId()));
    }







}
