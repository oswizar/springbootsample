package com.oswizar.springbootsample.mapper;

import com.oswizar.springbootsample.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface BookMapper extends BaseMapper<Book> {

    Book findBookById(int id);

    Book findBookByAuthorId(int authorId);

}
