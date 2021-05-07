package com.obitosnn.bean;

import java.util.List;

/**
 * <T> 为具体分页模块的JavaBean
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/11/15 15:11
 */
public class Page<T> {
    public static final int PAGE_SIZE = 4;
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    private Integer pageTotalCount;//总记录数
    private Integer pageSize = PAGE_SIZE;//每页显示数量
    private List<T> items;//当前页数据
    private String url;//分页请求地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
