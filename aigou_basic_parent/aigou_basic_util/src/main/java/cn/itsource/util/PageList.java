package cn.itsource.util;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
    private Long total = 0L;

    private List<T> rows =new ArrayList<T>() ;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageList(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

}
