package com.oswizar.springbootsample.mapper;

import com.oswizar.springbootsample.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    Book findBookById(int id);

    Book findBookByAuthorId(int authorId);

}
