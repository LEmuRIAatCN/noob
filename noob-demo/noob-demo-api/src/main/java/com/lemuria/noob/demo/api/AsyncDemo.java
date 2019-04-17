package com.lemuria.noob.demo.api;

import org.springframework.ui.ModelMap;

import java.util.concurrent.CompletableFuture;

public interface AsyncDemo {
    CompletableFuture<ModelMap> test1(String s);
}
