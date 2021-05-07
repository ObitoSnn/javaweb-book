package com.obitosnn.service.impl;

import com.github.pagehelper.PageHelper;
import com.obitosnn.bean.Book;
import com.obitosnn.bean.Page;
import com.obitosnn.dao.BookDao;
import com.obitosnn.service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/14 19:30
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> getPage(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置显示数据数量
        page.setPageSize(pageSize);
        //获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //获取总页码
        Integer pageTotal = pageTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        //数据有效边境检查
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageNo > page.getPageTotal()) {
            pageNo = page.getPageTotal();
        }
        //设置当前页码
        page.setPageNo(pageNo);
        //int begin = (page.getPageNo() - 1) * pageSize;
        //方式一：通过limit条件查询
//        List<Book> items = bookDao.queryForItems(begin, pageSize);
        //方式二：使用PageHelper
        PageHelper.startPage(pageNo, pageSize);
        List<Book> items = bookDao.queryBooks();
        ArrayList<Book> listItem = new ArrayList<>();
        for (Book book : items) {
            if (book.getStock() < 1) {
                bookDao.deleteBookById(book.getId());
            } else {
                listItem.add(book);
            }
        }
        page.setItems(listItem);
        return page;
    }

    @Override
    public Page getPageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page();
        //设置显示数据数量
        page.setPageSize(pageSize);
        //获取价格区间总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //获取总页码
        Integer pageTotal = pageTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        //数据有效边境检查
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageNo > page.getPageTotal()) {
            pageNo = page.getPageTotal();
        }
        //设置当前页码
        page.setPageNo(pageNo);
        int begin = (page.getPageNo() - 1) * pageSize;
        //方式一：通过价格区间获取当前页面数据
//        List<Book> items = bookDao.queryForItemsByPrice(begin, pageSize, min, max);
        //方式二
        PageHelper.startPage(pageNo, pageSize);
        List<Book> items = bookDao.queryForItemsByPriceByPageHelper(min, max);
        for (Book book : items) {
            if (book.getStock() < 1) {
                bookDao.deleteBookById(book.getId());
            }
        }
        page.setItems(items);
        return page;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
