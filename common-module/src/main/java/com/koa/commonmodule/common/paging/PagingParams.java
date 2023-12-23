package com.koa.commonmodule.common.paging;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PagingParams {

    private static final int DEFAULT_SIZE = 10;

    private Long cursor;
    private Integer size;

    public Long getCursor() {
        return cursor;
    }

    public Integer getSize() {
        if (size == null) {
            return DEFAULT_SIZE;
        }
        return size;
    }
}
