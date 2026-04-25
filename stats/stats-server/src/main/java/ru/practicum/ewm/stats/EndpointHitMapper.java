package ru.practicum.ewm.stats;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.dto.EndpointHitDto;

@Component
public class EndpointHitMapper {

    public EndpointHitDto toDto(EndpointHit endpointHitEntity) {
        return new EndpointHitDto(
                endpointHitEntity.getId(),
                endpointHitEntity.getApp(),
                endpointHitEntity.getUri(),
                endpointHitEntity.getIp(),
                endpointHitEntity.getTimestamp()
        );
    }

    public EndpointHit toEntity(EndpointHitDto dto) {
        return new EndpointHit(
                dto.getApp(),
                dto.getUri(),
                dto.getIp(),
                dto.getTimestamp()
        );
    }
}
