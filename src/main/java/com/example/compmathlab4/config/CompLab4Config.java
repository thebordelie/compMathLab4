package com.example.compmathlab4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.example.compmathlab4.reader",
        "com.example.compmathlab4.approximation",
        "com.example.compmathlab4.service",
        "com.example.compmathlab4.gui"
})
public class CompLab4Config {
}
