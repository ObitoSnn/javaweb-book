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
    public void updateBook(Book book);

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public Book queryBookById(Integer id);
    
    public List<Book> queryBooks();

    Page<Book> getPage(int pageNo, int pageSize);

    Page<Book> getPageByPrice(int pageNo, int pageSize, int min, int max);
}
