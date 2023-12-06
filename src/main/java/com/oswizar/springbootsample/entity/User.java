package com.oswizar.springbootsample.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 2890742645041401061L;
    @TableId
    private Integer id;
    private String username;
    private String password;
}
