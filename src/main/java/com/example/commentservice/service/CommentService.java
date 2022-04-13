package com.example.commentservice.service;


import com.example.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


}
