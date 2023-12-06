package com.oswizar.springbootsample.service.impl;

import com.oswizar.springbootsample.entity.Book;
import com.oswizar.springbootsample.mapper.BookMapper;
import com.oswizar.springbootsample.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
