package com.okccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: okccc
 * @Date: 2023/10/30 14:16:48
 * @Desc:
 * CREATE TABLE `t_book` (
 *   `book_id` int(11) NOT NULL AUTO_INCREMENT,
 *   `book_name` varchar(20) DEFAULT NULL,
 *   `price` double(10,2) DEFAULT NULL,
 *   `stock` int(10) unsigned DEFAULT NULL,
 *   PRIMARY KEY (`book_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Data
@AllArgsConstructor
public class Book {

    private Integer bookId;

    private String bookName;

    private Double price;

    private Integer stock;
}
