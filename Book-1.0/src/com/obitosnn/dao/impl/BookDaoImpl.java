package com.obitosnn.dao.impl;


import com.obitosnn.bean.Book;
import com.obitosnn.dao.BaseDao;
import com.obitosnn.dao.BookDao;

import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 18:56
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`id`, `name`, `author`, `price`, `sales`, `stock`, `img_path`) values(?, ?, ?, ?, ?, ?, ?)";
        return update(sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?, `author` = ?, `price` = ?, `sales` = ?, `stock` = ?, `img_path` = ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book where `id` = ?";
        return queryForOne(sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book";
        return queryForList(sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) getValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book limit ?, ?";
        return queryForList(sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) getValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book" +
                " where price between ? and ? order by `price` asc limit ?, ?";
        return queryForList(sql, min, max, begin, pageSize);
    }

}
