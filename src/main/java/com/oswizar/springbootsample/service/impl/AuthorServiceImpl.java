package com.oswizar.springbootsample.service.impl;

import com.oswizar.springbootsample.service.AuthorService;
import com.oswizar.springbootsample.entity.Author;
import com.oswizar.springbootsample.mapper.AuthorMapper;
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
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

}
