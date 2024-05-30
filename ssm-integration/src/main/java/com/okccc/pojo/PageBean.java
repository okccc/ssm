package com.okccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/27 18:36:53
 * @Desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

    // 当前页码
    private int currentPage;

    // 每页显示的数据量
    private int pageSize;

    // 数据总条数
    private long total;

    // 当前页的数据集合
    private List<T> data;

}
