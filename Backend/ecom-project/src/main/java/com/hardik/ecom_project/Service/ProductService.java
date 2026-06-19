package com.hardik.ecom_project.Service;

import com.hardik.ecom_project.Model.Product;
import com.hardik.ecom_project.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElse(null    );
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    public Product updateProduct(Long id, Product product, MultipartFile imageFile) throws IOException {

        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public void deleteProduct(Long id) {

        repo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProducts(keyword);
    }
}
