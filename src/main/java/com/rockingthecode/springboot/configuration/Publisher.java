package com.rockingthecode.springboot.configuration;

import lombok.Data;

@Data
public class Publisher {
    private String address;
    private Long taxNumber;
    private String webSite;
}
