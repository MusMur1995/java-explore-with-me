package ru.practicum.ewm.controller.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;
import ru.practicum.ewm.service.comment.CommentService;

@RestController
@RequestMapping("/users/{userId}/events/{eventId}/comments")
@RequiredArgsConstructor
public class PrivateCommentController {
    private final CommentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            @RequestBody @Valid NewCommentDto dto
    ) {
        return service.create(eventId, userId, dto);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            @PathVariable Long commentId,
            @RequestBody @Valid NewCommentDto dto
    ) {
        return service.update(eventId, userId, commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            @PathVariable Long commentId
    ) {
        service.deleteByUser(eventId, userId, commentId);
    }
}
