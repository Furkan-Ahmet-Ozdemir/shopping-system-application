package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Invoice {
    private BigDecimal total;

    private LocalDateTime createDate;

    public Invoice( BigDecimal total) {
        this.createDate = LocalDateTime.now();
        this.total = total;
    }

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total){
        this.total = total;
    }

    @Override
    public String toString(){
        return "Invoice{" +
                "total=" + total +
                ", createDate=" + createDate +
                '}';
    }
}
