/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.dto;

import java.util.List;
import org.springframework.data.domain.Page;

public class Pagination<E> {

    private List<E> content;

    private int total = 0;

    private int perPage = 10;

    private int page = 1;

    private int totalPage = 0;

    private boolean firstPage = false;

    private boolean lastPage = false;

    public Pagination() {
    }

    public Pagination(List<E> content, Page pageable) {
        this.content = content;
        this.total = Math.toIntExact(pageable.getTotalElements());
        this.page = pageable.getPageable().getPageNumber();
        this.perPage = pageable.getPageable().getPageSize();
        this.totalPage = pageable.getTotalPages();
        this.firstPage = pageable.isFirst();
        this.lastPage = pageable.isLast();
    }

    public List<E> getContent() {
        return content;
    }

    public void setContent(List<E> content) {
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

}
