package project.maribo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.maribo.exception.CommentNotFoundException;
import project.maribo.exception.UserNotFoundException;
import project.maribo.exception.PostNotFoundException;
import project.maribo.exception.UserNotMatchException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> postNotFoundException(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("해당 게시물을 찾을 수 없습니다.");
    }

    @ExceptionHandler
    public ResponseEntity<String> userNotMatchException(UserNotMatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("해당 요청에 대한 권한이 없습니다.");
    }

    @ExceptionHandler
    public ResponseEntity<String> memberNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("해당 회원이 존재하지 않습니다.");
    }

    @ExceptionHandler
    public ResponseEntity<String> commentNotFoundException(CommentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("해당 댓글이 존재하지 않습니다.");
    }
}
