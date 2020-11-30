package com.xiexing.springbootdemo.service;

import com.xiexing.springbootdemo.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
public interface IBookService extends IService<Book> {


    Book findBookById(int id);

}
