package com.oswizar.springbootsample.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 *
 * </p>
 *
 * @author oswizar
 * @since 2020-08-13
 */
@Data
@TableName("t_author")
public class Author implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer age;

    @TableField(exist = false)
    @Valid
    @NotEmpty(message = "作者书籍不能为空")
    List<Book> bookList;
}
