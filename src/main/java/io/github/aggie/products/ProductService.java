package io.github.aggie.products;

import io.github.aggie.common.PagedResult;
import io.github.aggie.common.retry.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Log
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @CacheEvict("products")
    @Retry
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Cacheable("products")
    public List<Product> getByName(String name) {
        log.info("Reading products from database...");
        return productRepository.findByNameContaining(name);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        var productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
