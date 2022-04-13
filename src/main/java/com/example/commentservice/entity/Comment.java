package com.example.commentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "CommentService")
public class Comment {
    @Id
    private String commentId;

    private String postID;

    @NotEmpty(message = "Comment By is required")
    private String commentedBy;

    @NotEmpty(message = "Comment is required")
    private String comment;

    private Date createdAt;

    private Date updatedAt;
}
