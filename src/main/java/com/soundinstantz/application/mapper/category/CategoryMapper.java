package com.soundinstantz.application.mapper.category;
import com.soundinstantz.application.dto.category.CategoryDto;
import com.soundinstantz.application.mapper.BaseMapper;
import com.soundinstantz.domain.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper extends BaseMapper<Category, CategoryDto> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
