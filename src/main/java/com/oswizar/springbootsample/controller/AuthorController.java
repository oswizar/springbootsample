package com.oswizar.springbootsample.controller;


import com.oswizar.springbootsample.entity.Author;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
@Slf4j
@Validated
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping("/findAuthorById")
    public ResponseResult findAuthorById(@NotNull @Min(value = 1, message = "用户id不满足要求") Integer id) {
        return ResponseResult.success(authorService.getById(id));

    }

    @PostMapping("/save")
    public ResponseResult save(@Validated @RequestBody Author author) {
        log.info("author:{}", author);
        return ResponseResult.success("success");
    }

}

