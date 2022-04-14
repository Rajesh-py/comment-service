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

    public String deletebyCommentId(String commentId) {
        if(commentRepository.findById(commentId).isPresent()){
            this.commentRepository.deleteById(commentId);
            return Constants.successCode;
        }
        else{
            throw new CommentNotFoundException(Constants.errorCode);
        }
    }











}
