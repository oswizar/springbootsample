package com.xiexing.springbootdemo.service;

import com.xiexing.springbootdemo.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiexing.springbootdemo.entity.Book;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
public interface IAuthorService extends IService<Author> {

    Author findAuthorById(int id);

}
