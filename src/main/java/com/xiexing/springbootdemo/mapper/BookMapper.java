package com.xiexing.springbootdemo.mapper;

import com.xiexing.springbootdemo.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
public interface BookMapper extends BaseMapper<Book> {

    Book findBookById(int id);

    Book findBookByAuthorId(int authorId);

}
