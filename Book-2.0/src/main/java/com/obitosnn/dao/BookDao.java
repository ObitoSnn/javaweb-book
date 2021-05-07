package com.obitosnn.dao;


import com.obitosnn.bean.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 18:53
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);
    /**
     * 删除图书
     * @param id
     * @return
     */
    public int deleteBookById(@Param("id") Integer id);
    /**
     * 修改图书
     * @param book
     * @return
     */
    public int updateBook(Book book);
    /**
     * 通过id查询图书信息
     * @param id
     * @return
     */
    public Book queryBookById(@Param("id") Integer id);
    /**
     * 查询所有图书信息
     * @return
     */
    public List<Book> queryBooks();
    /**
     * 查询总页码数
     * @return
     */
    Integer queryForPageTotalCount();
    @Deprecated
    List<Book> queryForItems(@Param("begin") int begin, @Param("pageSize") int pageSize);
    /**
     * 获取价格区间总记录数
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回价格区间总记录数
     */
    Integer queryForPageTotalCountByPrice(@Param("min") int min, @Param("max") int max);
    /**
     * 通过价格区间获取页面数据
     * @param begin 起始位置
     * @param pageSize 当前页面数据数
     * @param min 价格最小zhi
     * @param max 价格最大值
     * @return 返回价格区间内的数据
     */
    @Deprecated
    List<Book> queryForItemsByPrice(@Param("begin") int begin, @Param("pageSize") int pageSize,
                                    @Param("min") int min, @Param("max") int max);
    /**
     * 查询全部商品项，使用PageHelper做分页
     * @return
     */
    List<Book> queryForItemsByPageHelper();
    /**
     * 通过价格区间获取全部商品项，使用PageHelper做分页
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForItemsByPriceByPageHelper(@Param("min") int min, @Param("max") int max);
}
