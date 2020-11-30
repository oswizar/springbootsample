package com.xiexing.springbootdemo.mapper;

import com.xiexing.springbootdemo.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiexing.springbootdemo.entity.Book;

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
