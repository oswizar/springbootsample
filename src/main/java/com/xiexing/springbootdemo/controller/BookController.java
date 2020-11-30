package com.xiexing.springbootdemo.controller;


import com.xiexing.springbootdemo.entity.Book;
import com.xiexing.springbootdemo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;


    @PostMapping("/findBookById")
    public Object findBookById(@RequestBody Book book) {
        return bookService.findBookById(book.getId());

    }

}

