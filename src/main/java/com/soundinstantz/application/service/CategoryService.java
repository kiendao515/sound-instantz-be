package com.soundinstantz.application.service;


import com.soundinstantz.application.dto.category.CategoryDto;
import com.soundinstantz.domain.category.Category;
import com.soundinstantz.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CategoryDto convertToDTO(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .slug(category.getSlug())
                //.description(category.getDescription())
                .build();
    }
}
