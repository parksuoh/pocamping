package com.camping.camping.exceptions;

public class CategoryNotExist extends RuntimeException {

    public CategoryNotExist() {
        super("카테고리가 존재하지 않습니다.");
    }
}
