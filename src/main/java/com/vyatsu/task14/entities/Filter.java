package com.vyatsu.task14.entities;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class Filter {
    private String title;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
    public BigDecimal getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Filter(String title, BigDecimal minPrice, BigDecimal maxPrice) {
        this.title = title;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
