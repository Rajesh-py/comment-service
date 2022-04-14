package com.example.commentservice.repository;

import com.example.commentservice.entity.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface CommentRepository extends MongoRepository<Comment, String> {






}
