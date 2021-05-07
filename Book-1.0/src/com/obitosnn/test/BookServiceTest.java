package com.obitosnn.test;


import com.obitosnn.bean.Book;
import com.obitosnn.service.BookService;
import com.obitosnn.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 19:40
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "venom", "venom", new BigDecimal(4654), 0, 0, null));
    }

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "venom", "venom", new BigDecimal(8987), 9879, 23423, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void getPage() {
        System.out.println(bookService.getPage(1, 4));
    }
    @Test
    public void getPageByPrice() {
        System.out.println(bookService.getPageByPrice(2, 4, 10 , 50));
    }
}