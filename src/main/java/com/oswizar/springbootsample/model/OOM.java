package com.oswizar.springbootsample.model;

import lombok.Data;

@Data
public class OOM {
    private String name;
    private final byte[] data = new byte[1024 * 1024 * 1024];
}
