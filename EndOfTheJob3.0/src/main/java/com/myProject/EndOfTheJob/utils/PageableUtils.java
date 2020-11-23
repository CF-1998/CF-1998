package com.myProject.EndOfTheJob.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
	/**
     * 获取基础分页对象
     * @param page 获取第几页
     * @param size 每页条数
     * @param dtos 排序对象数组
     * @return
     */
    static final int DEFAULTPAGESIZE=5;
    public static Pageable basicPage(Integer page, Integer size, SortDto... dtos) {
        Sort sort = SortUtils.basicSort(dtos);
        page = (page==null || page<0)?0:page;
        size = (size==null || size<=0)? DEFAULTPAGESIZE:size;
        Pageable pageable =PageRequest.of(page, size, sort);
        return pageable;
    }

    /**
     * 获取基础分页对象，每页条数默认10条
     *  - 默认以id降序排序
     * @param page 获取第几页
     * @return
     */
    public static Pageable basicPage(Integer page) {
        return basicPage(page, DEFAULTPAGESIZE, new SortDto("desc", "id"));
    }
    /**
          * 获取基础分页对象，每页条数size条
     *  - 默认以id降序排序
     * @param page 获取第几页
     * @return
     */
    public static Pageable basicPage(Integer page,Integer size) {
    	return basicPage(page, size, new SortDto("asc", "id"));
    }

    /**
     * 获取基础分页对象，每页条数默认10条
     * @param page 获取第几页
     * @param dtos 排序对象数组
     * @return
     */
    public static Pageable basicPage(Integer page, SortDto... dtos) {
        return basicPage(page, DEFAULTPAGESIZE, dtos);
    }

    /**
     * 获取基础分页对象，排序方式默认降序
     * @param page 获取第几页
     * @param size 每页条数
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size, String orderField) {
        return basicPage(page, size, new SortDto("desc", orderField));
    }
    /**
     * 获取基础分页对象
     * @param page 获取第几页
     * @param size 每页条数
     * @param orderField 排序字段
     * @param sortD  排序方式 "desc" or "asc"
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size, String orderField,String sortD) {
    	return basicPage(page, size, new SortDto(sortD, orderField));
    }

    /**
     * 获取基础分页对象
     *  - 每页条数默认10条
     *  - 排序方式默认降序
     * @param page 获取第几页
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page, String orderField) {
        return basicPage(page, DEFAULTPAGESIZE, new SortDto("desc", orderField));
    }
    /**
     * 获取基础分页对象
     *  - 每页条数默认10条
     *  - 排序方式默认降序
     * @param page 获取第几页
     * @param orderField 排序字段
     * @param sortD  排序方式 "desc" or "asc"
     * @return
     */
    public static Pageable basicPage(Integer page, String orderField,String sortD) {
    	return basicPage(page, DEFAULTPAGESIZE, new SortDto(sortD, orderField));
    }
}
