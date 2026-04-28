package ru.practicum.ewm.service.category;

import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.NewCategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto create(NewCategoryDto dto);

    void delete(Long id);

    CategoryDto update(Long id, CategoryDto dto);

    List<CategoryDto> getAll(int from, int size);

    CategoryDto getById(Long id);
}
