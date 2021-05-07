package com.obitosnn.test;

import com.obitosnn.bean.Book;
import com.obitosnn.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 19:12
 */
public class BookDaoTest {
    private BookDaoImpl dao = new BookDaoImpl();
    @Test
    public void addBook() {
        dao.addBook(new Book(null, "obitosnn", "obitosnn", new BigDecimal(99999), 999, 0, null));
    }

    @Test
    public void deleteBookById() {
        dao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        dao.updateBook(new Book(21, "obitosnn", "spiderman", new BigDecimal(99900), 889, 779, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(dao.queryBookById(15));
    }

    @Test
    public void queryBooks() {
        for (Book book : dao.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(dao.queryForPageTotalCount());
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(dao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForItems() {
        System.out.println(dao.queryForItems(0, 4));
    }
    @Test
    public void queryForItemsByPrice() {
        System.out.println(dao.queryForItemsByPrice(0, 4, 10, 50));
    }
}