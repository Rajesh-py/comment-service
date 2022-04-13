package com.example.commentservice.feign;

import com.example.commentservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface UserService {
    @GetMapping("/api/v1/users/{userId}")
    public User findID(@PathVariable("userId") String userId);
}
