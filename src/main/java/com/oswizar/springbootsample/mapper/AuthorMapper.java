package com.oswizar.springbootsample.mapper;

import com.oswizar.springbootsample.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
public interface AuthorMapper extends BaseMapper<Author> {

    Author findById(int id);

    Author findAuthorById(int id);

}
