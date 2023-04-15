package com.example.fastcampusmysql.utils;

public record CursorRequest(Long key, int size) {
    // cusor는 유니크함이 보장되어야함
    public static final Long NONE_KEY = -1L;

    public Boolean hasKey() {
        return key != null;
    }
    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}
