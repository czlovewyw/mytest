package org.leochen.samples.web.controllers.hotels;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;



/**
 * User: leochen
 * Date: 11-12-1
 * Time: 下午1:12
 */
public class SearchCriteria implements Serializable {
    public static final int DEFAUTL_PAGE_SIZE = 5;
    private String searchString;
    private int pageSize;
    private int page;

    public SearchCriteria(){
        page=0;
        pageSize = DEFAUTL_PAGE_SIZE;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Min(DEFAUTL_PAGE_SIZE)
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Min(0)
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
