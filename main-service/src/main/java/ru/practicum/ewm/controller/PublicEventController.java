package ru.practicum.ewm.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.EventFullDto;
import ru.practicum.ewm.dto.EventPublicSearchParams;
import ru.practicum.ewm.dto.EventShortDto;
import ru.practicum.ewm.service.EventService;
import ru.practicum.ewm.stats.client.StatsClient;
import ru.practicum.ewm.stats.dto.EndpointHitDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("events")
@RequiredArgsConstructor
@Slf4j
public class PublicEventController {
    private final EventService service;
    private final StatsClient statsClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventShortDto> getEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        log.info("GET /events called: text={}, categories={}, paid={}, rangeStart={}, " +
                        "rangeEnd={}, onlyAvailable={}, sort={}, from={}, size={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);


        try {
            statsClient.saveHit(new EndpointHitDto(
                    null,
                    "main-service",
                    request.getRequestURI(),
                    request.getRemoteAddr(),
                    LocalDateTime.now()
            ));
        } catch (Exception e) {
            log.warn("Failed to send hit to stats-server: {}", e.getMessage());
        }

        List<EventShortDto> result = service.getPublicEvents(new EventPublicSearchParams(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sort,
                from,
                size
        ), request);

        log.info("GET /events result size={}, result.size()");
        return result;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getEventById(
            @PathVariable long id,
            HttpServletRequest request
    ) {

        try {
            statsClient.saveHit(new EndpointHitDto(
                    null,
                    "main-service",
                    request.getRequestURI(),
                    request.getRemoteAddr(),
                    LocalDateTime.now()
            ));
            log.info("Hit sent for event id: {}", id);
        } catch (Exception e) {
            log.warn("Failed to send hit for event {}: {}", id, e.getMessage());
        }

        return service.getPublicEventById(id, request);
    }
}
