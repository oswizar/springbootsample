package com.oswizar.springbootsample.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String name;
    private BigDecimal price;
    private Integer authorId;
    private String author;
}
