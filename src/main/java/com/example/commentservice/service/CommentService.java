package com.example.commentservice.service;


import com.example.commentservice.entity.Comment;
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

    public CommentDto postComment(Comment comment, String postID) {
        comment.setPostID(postID);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        this.commentRepository.save(comment);
        return new CommentDto(comment.getCommentId(),
                userFeign.findID(comment.getCommentedBy()), comment.getComment(),
                comment.getCreatedAt(), comment.getUpdatedAt(),
                likeFeign.countLike(comment.getCommentId()));

    }


}
