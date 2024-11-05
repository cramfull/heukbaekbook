package com.nhnacademy.heukbaekfrontend.member.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authClient", url = "http://localhost:8082")
public interface AuthClient {

    @PostMapping("/api/auth/logout")
    ResponseEntity<String> logout();
}
