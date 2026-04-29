package ru.practicum.ewm.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.service.comment.CommentService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCommentController {
    private final CommentService service;

    @DeleteMapping("/events/{eventId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long eventId,
            @PathVariable Long commentId
    ) {
        service.deleteByAdmin(eventId, commentId);
    }

    @PatchMapping("/events/{eventId}/comments/{commentId}/moderate")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto moderateComment(
            @PathVariable Long eventId,
            @PathVariable Long commentId,
            @RequestParam String action
    ) {
        return service.moderate(eventId, commentId, action);
    }
}
