package com.obitosnn.dao;


import com.obitosnn.bean.Book;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 18:53
 */
public interface BookDao {

     int addBook(Book book);

     int deleteBookById(Integer id);

     int updateBook(Book book);

     Book queryBookById(Integer id);

     List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForItems(int begin, int pageSize);
    /**
     * 获取价格区间总记录数
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回价格区间总记录数
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 通过价格区间获取页面数据
     * @param begin 起始位置
     * @param pageSize 当前页面数据数
     * @param min 价格最小zhi
     * @param max 价格最大值
     * @return 返回价格区间内的数据
     */
    List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max);
}
