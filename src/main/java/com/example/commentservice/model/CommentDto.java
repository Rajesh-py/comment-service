package com.example.commentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    @Id
    private String commentId;

    @NotEmpty(message = "commentedBy is required")
    private User commentedBy;

    @NotEmpty(message = "comment is required")
    private String comment;
    private Date createdAt;

    private Date updatedAt;

    private int likesCount;


}
