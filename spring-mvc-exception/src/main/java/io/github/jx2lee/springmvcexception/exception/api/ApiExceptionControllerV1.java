package io.github.jx2lee.springmvcexception.exception.api;

import io.github.jx2lee.springmvcexception.exception.exception.BadRequestException;
import io.github.jx2lee.springmvcexception.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionControllerV1 {

    @GetMapping("/api/v1/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello! " + id);
    }

    @GetMapping("/api/v1/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/v1/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad");
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

    @GetMapping("/api/v1/default-handler-ex")
    public String defaultException(@RequestParam Integer data) {
        return "ok, " + data;
    }
}
