package com.example.commentservice.repository;

import com.example.commentservice.entity.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, String> {
    public List<Comment> findByPostID(String postID);






}
