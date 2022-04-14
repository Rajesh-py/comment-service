package com.example.commentservice.repository;

import com.example.commentservice.entity.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, String> {
    public List<Comment> findByPostID(String postID);
    public List<Comment> findBypostID(String postID, Pageable page);

}
