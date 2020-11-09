package com.study.day08;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.study.day08")
public class ColorConfig {

    private String className = "ColorConfig";

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ColorConfig{" +
                "className='" + className + '\'' +
                '}';
    }
}
