package ru.practicum.ewm.service.comment;

import ru.practicum.ewm.dto.comment.CommentDto;
import ru.practicum.ewm.dto.comment.NewCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto create(Long eventId, Long userId, NewCommentDto dto);

    CommentDto update(Long eventId, Long userId, Long commentId, NewCommentDto dto);

    void deleteByAdmin(Long eventId, Long commentId);

    void deleteByUser(Long eventId, Long userId, Long commentId);

    List<CommentDto> getAll(Long eventId, int from, int size);

    CommentDto moderate(Long eventId, Long commentId, String action);
}
