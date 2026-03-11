package com.oswizar.springbootsample.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@TableName("t_author")
public class Author implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "作者id不能为空")
    private Integer id;
    @NotNull(message = "作者姓名不能为空")
    private String name;
    @NotNull(message = "作者年龄不能为空")
    private Integer age;

    @TableField(exist = false)
    @Valid
    @NotEmpty(message = "作者书籍不能为空")
    List<Book> bookList;
}
