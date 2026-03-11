package com.oswizar.springbootsample.controller;


import com.oswizar.springbootsample.entity.Book;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.service.BookService;
import com.oswizar.springbootsample.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @Autowired
    private HttpServletRequest request;


    @PostMapping("/findBookById")
    public ResponseResult findBookById(@RequestBody Book book) {
        Object result = bookService.getById(book.getId());
        return ResponseResult.success(result);
    }

    @PostMapping("/testBook")
    public ResponseResult testBook(@RequestBody Book book) {
        System.out.println(book);

        System.out.println(request.getServletPath());

        List<Object> list  = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        RedisUtils.set("book", book);

//        RedisUtils.del("keys");



//        RedisUtils.lSet("indices", null);
//        RedisUtils.lSet("indicess", list);

//        RedisUtils.lRemove("indicess",1,5);

//        List<Object> indicess = RedisUtils.lGet("indicess", 0, -1);
//        System.out.println(indicess);

//        int i = indicess.indexOf(3);
//        System.out.println(i);
//        System.out.println(indicess.get(i + 1));

//        RedisUtils.llSetSingle("indicess",8);
        return ResponseResult.success(book);

    }

}

