package com.oswizar.springbootsample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("t_book")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "书籍名称不能为空")
    private String name;
    private BigDecimal price;
    private Integer authorId;
    private String author;
}
