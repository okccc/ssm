package com.okccc.mapper;

import com.okccc.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/10/30 14:18:35
 * @Desc:
 */
public interface BookMapper {

    /**
     * 批量添加
     */
    void insertBatch(@Param("bookList") List<Book> bookList);

    /*
     * 批量更新
     */
    void updateBatch(@Param("bookList") List<Book> bookList);

    /**
     * 批量删除
     */
    void deleteBatch(@Param("bookIds") Integer[] bookIds);

    /**
     * 批量查询
     */
    List<Book> selectBatch(@Param("bookIds") Integer[] bookIds);

    /**
     * 条件查询
     */
    List<Book> getBookByWhere(@Param("bookName") String bookName, @Param("price") Double price);
    List<Book> getBookByTrim(@Param("bookName") String bookName, @Param("price") Double price);
    List<Book> getBookByChoose(@Param("bookName") String bookName, @Param("price") Double price);
}
