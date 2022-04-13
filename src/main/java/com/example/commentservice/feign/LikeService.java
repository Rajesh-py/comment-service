package com.example.commentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="like-service")
public interface LikeService {
    @GetMapping("/api/v1/postsOrComments/{postOrCommentId}/likes/count")
    public int countLike( @PathVariable("postOrCommentId") String postOrCommentID);

}
