package com.oswizar.springbootsample.controller;


import com.oswizar.springbootsample.entity.Author;
import com.oswizar.springbootsample.service.IAuthorService;
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
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;


    @PostMapping("/findAuthorById")
    public Object findAuthorById(@RequestBody Author author) {
        return authorService.findAuthorById(author.getId());

    }

}

