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



@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userFeign;

    @Autowired
    LikeService likeFeign;

    public CommentDto findByCommentId(String commentId) {
        try{
            Comment comment= commentRepository.findById(commentId).get();

            return new CommentDto(comment.getCommentId(),
                    userFeign.findID(comment.getCommentedBy()),
                    comment.getComment(),comment.getCreatedAt(),comment.getUpdatedAt(),
                    likeFeign.countLike(comment.getCommentId()));

        }
        catch(Exception e){
            throw  new CommentNotFoundException(Constants.errorCode);
        }
    }



}
