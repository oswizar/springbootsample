package com.xiexing.springbootdemo.controller;


import com.xiexing.springbootdemo.entity.Book;
import com.xiexing.springbootdemo.service.IBookService;
import com.xiexing.springbootdemo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private HttpServletRequest request;


    @PostMapping("/findBookById")
    public Object findBookById(@RequestBody Book book) {
        return bookService.findBookById(book.getId());

    }

    @PostMapping("/testBook")
    public Object testBook(@RequestBody Book book) {
        System.out.println(book);

        System.out.println(request.getServletPath());

        List<Object> list  = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        redisUtils.set("book", book);

//        redisUtils.del("keys");



//        redisUtils.lSet("indices", null);
//        redisUtils.lSet("indicess", list);

//        redisUtils.lRemove("indicess",1,5);

//        List<Object> indicess = redisUtils.lGet("indicess", 0, -1);
//        System.out.println(indicess);

//        int i = indicess.indexOf(3);
//        System.out.println(i);
//        System.out.println(indicess.get(i + 1));

//        redisUtils.llSetSingle("indicess",8);
        return book;

    }

}

