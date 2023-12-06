package com.oswizar.springbootsample.mapper;

import com.oswizar.springbootsample.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    Book findBookById(int id);

    Book findBookByAuthorId(int authorId);

}
