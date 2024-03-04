package org.tolga.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.tolga.dto.request.CreateProductRequestDto;
import org.tolga.dto.request.UpdateProductRequestDto;
import org.tolga.exception.ErrorType;
import org.tolga.exception.ProductServiceException;
import org.tolga.mapper.IProductMapper;
import org.tolga.repository.IProductRepository;
import org.tolga.repository.entity.Product;
import org.tolga.repository.enums.EStatus;
import org.tolga.utility.ServiceManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends ServiceManager<Product, Long> {

    private final IProductRepository repository;

    public ProductService(JpaRepository<Product, Long> jpaRepository, IProductRepository repository) {
        super(jpaRepository);
        this.repository = repository;
    }
    public Product createProduct(CreateProductRequestDto dto) {
        if (repository.existsByProductName(dto.getProductName())) {
            throw new ProductServiceException(ErrorType.PRODUCT_ALREADY_EXISTS);
        }
        Product product = IProductMapper.INSTANCE.createProductRequestDtoToProduct(dto);
        product.setStatus(EStatus.ACTIVE);
        save(product);
        return product;
    }
    public String updateProduct(UpdateProductRequestDto dto) {
        Optional<Product> optionalProduct = findById(dto.getId());
        if (optionalProduct.isEmpty()) {
            throw new ProductServiceException(ErrorType.PRODUCT_NOT_FOUND);
        }
        Product existingProduct = optionalProduct.get();

        if (existingProduct.getStatus().equals(EStatus.DELETED)) {
            throw new ProductServiceException(ErrorType.PRODUCT_ALREADY_DELETED);
        }
        if (repository.existsByProductName(dto.getProductName())) {
            throw new ProductServiceException(ErrorType.PRODUCT_ALREADY_EXISTS);
        }
        if (dto.getProductName()!=null){
            existingProduct.setProductName(dto.getProductName());
        }
        if (dto.getPrice()!=null){
            existingProduct.setPrice(dto.getPrice());
        }
        if (dto.getStock()!=null){
            existingProduct.setStock(dto.getStock());
        }
        if (dto.getDescription()!=null){
            existingProduct.setDescription(dto.getDescription());
        }
        if (dto.getPhoto()!=null){
            existingProduct.setPhoto(dto.getPhoto());
        }
        if (dto.getBrand()!=null){
            existingProduct.setBrand(dto.getBrand());
        }
        update(existingProduct);
        return "Successfully updated";
    }
    public String deleteProduct(Long id) {
        Optional<Product> optionalProduct = findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductServiceException(ErrorType.PRODUCT_NOT_FOUND);
        }
        Product existingProduct = optionalProduct.get();
        if (existingProduct.getStatus().equals(EStatus.DELETED)) {
            throw new ProductServiceException(ErrorType.PRODUCT_ALREADY_DELETED);
        }
        existingProduct.setStatus(EStatus.DELETED);
        update(existingProduct);
        return existingProduct.getProductName() + "named has been deleted";
    }

    public Product getProduct(Long id) {
        Optional<Product> optionalProduct = findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductServiceException(ErrorType.PRODUCT_NOT_FOUND);
        }
        Product existingProduct = optionalProduct.get();
        if (existingProduct.getStatus().equals(EStatus.ACTIVE)) {
                return existingProduct;
            } else {
                throw new ProductServiceException(ErrorType.PRODUCT_NOT_ACTIVE);
            }
    }


    public List<Product> getAllProducts() {
        return findAll().stream()
                .filter(item -> item.getStatus() == EStatus.ACTIVE)
                .collect(Collectors.toList());

    }
}
