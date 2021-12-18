package com.oswizar.springbootsample.service;

import com.oswizar.springbootsample.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;

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
