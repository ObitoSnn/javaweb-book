package com.obitosnn.service;


import com.obitosnn.bean.Book;
import com.obitosnn.bean.Page;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 19:27
 */
public interface BookService {
    /**
     * 更新图书
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 添加图书
     * @param book 待添加的图书
     */
    public void addBook(Book book);

    /**
     * 通过id删除图书
     * @param id 图书的id
     */
    public void deleteBookById(Integer id);

    /**
     * 通过id查询图书
     * @param id 图书的id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return 全部图书的信息
     */
    public List<Book> queryBooks();

    /**
     * 获取Page对象
     * @param pageNo 当前页码
     * @param pageSize 当前页码显示数据数
     * @return 返回Page对象
     */
    Page<Book> getPage(int pageNo, int pageSize);

    /**
     * 通过价格区间获取Page对象
     * @param pageNo 当前页码
     * @param pageSize 当前页码显示数据数
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回Page价格区间的Page对象
     */
    Page<Book> getPageByPrice(int pageNo, int pageSize, int min, int max);
}
