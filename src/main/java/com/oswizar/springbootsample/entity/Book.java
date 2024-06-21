package com.oswizar.springbootsample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
@Data
@TableName("t_book")
public class Book implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotBlank
    private String name;
    private BigDecimal price;
    private Integer authorId;
    private String author;
}
