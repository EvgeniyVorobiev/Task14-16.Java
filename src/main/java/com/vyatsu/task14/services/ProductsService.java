package com.vyatsu.task14.services;

import com.vyatsu.task14.entities.Filter;
import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.repositories.IProductRepository;
import com.vyatsu.task14.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll(ProductSpecification.titleContainsWord(""));
    }

    public List<Integer> getPages() {
        List<Integer> pages = new ArrayList<>();

        int totalProducts = getProducts().size();
        int productsPerPage = 5;

        int countOfPages = (int) Math.ceil((float) totalProducts / productsPerPage);

        for (int i = 0; i < countOfPages; i++) {
            pages.add(i);
        }

        return pages;
    }

    public Page<Product> getFilteredProductsAndPage(Filter filter, PageRequest pageable) {
        Specification<Product> specification = Specification.where(null);

        if (filter.getTitle() != null) {
            specification = specification.and(ProductSpecification.titleContainsWord(filter.getTitle()));
        }

        if (filter.getMinPrice() != null) {
            specification = specification.and(ProductSpecification.priceGreaterThanOrEq(filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            specification = specification.and(ProductSpecification.priceLesserThanOrEq(filter.getMaxPrice()));
        }

        return productRepository.findAll(specification, pageable);
    }

    public void viewProduct(Product product) {
        product.setView(product.getView() + 1);
        saveOrUpdate(product);
    }

    public List<Product> getTopProducts() {
        return ((List<Product>) productRepository.findAll()).stream().sorted((o1, o2) -> o2.getView() - o1.getView()).limit(3).collect(Collectors.toList());
    }

    public Product read(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Secured(value = "ROLE_ADMIN")
    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Secured(value = "ROLE_ADMIN")
    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
