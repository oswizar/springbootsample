package com.xiexing.springbootdemo.service.impl;

import com.xiexing.springbootdemo.entity.Author;
import com.xiexing.springbootdemo.entity.Book;
import com.xiexing.springbootdemo.mapper.AuthorMapper;
import com.xiexing.springbootdemo.mapper.BookMapper;
import com.xiexing.springbootdemo.service.IAuthorService;
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
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements IAuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Author findAuthorById(int id) {
        return authorMapper.findAuthorById(id);
    }
}
