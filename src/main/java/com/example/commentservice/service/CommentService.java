package com.example.commentservice.service;


import com.example.commentservice.constants.Constants;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.exception.CommentNotFoundException;
import com.example.commentservice.feign.LikeService;
import com.example.commentservice.feign.UserService;
import com.example.commentservice.model.CommentDto;
import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userFeign;

    @Autowired
    LikeService likeFeign;

    public List<CommentDto> showCommentsByPostId(String postID,Integer page,Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        CommentDto commentDTO=new CommentDto();
        Pageable firstPage = (Pageable) PageRequest.of(page-1, pageSize);
        List<Comment> commentModels  = commentRepository.findBypostID(postID, (org.springframework.data.domain.Pageable) firstPage);
        if(commentModels.isEmpty()){
            throw  new CommentNotFoundException(Constants.errorCode);
        }
        List<CommentDto> commentDTOS = new ArrayList<>();
        for(Comment commentModel:commentModels){
            CommentDto commentDTO1=new CommentDto(commentModel.getCommentId(),
                    userFeign.findID(commentModel.getCommentedBy()),
                    commentModel.getComment(),commentModel.getCreatedAt(),commentModel.getUpdatedAt(),
                    likeFeign.countLike(commentModel.getCommentId()));
            commentDTOS.add(commentDTO1);
        }
        return  commentDTOS;


    }


}
