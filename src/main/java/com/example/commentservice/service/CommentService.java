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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<CommentDto> showCommentsByPostId(String postID,Integer page,Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        CommentDto commentDTO=new CommentDto();
        Pageable firstPage = PageRequest.of(page-1, pageSize);
        List<Comment> commentModels  = commentRepository.findBypostID(postID, (java.awt.print.Pageable) firstPage);
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


    public String deletebyCommentId(String commentId) {
        if(commentRepository.findById(commentId).isPresent()){
            this.commentRepository.deleteById(commentId);
            return Constants.successCode;
        }
        else{
            throw new CommentNotFoundException(Constants.errorCode);
        }
    }

    public int commentCount(String postID){
        int count=this.commentRepository.findByPostID(postID).size();
        return count;

    }
}
