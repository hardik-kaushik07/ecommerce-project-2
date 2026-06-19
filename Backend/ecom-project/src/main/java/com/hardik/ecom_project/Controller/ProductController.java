package com.hardik.ecom_project.Controller;

import com.hardik.ecom_project.Model.Product;
import com.hardik.ecom_project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174")
public class ProductController {

    @Autowired
    private ProductService service;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
        @GetMapping("/products")
    public ResponseEntity<List<Product>> getProduct(){

            List<Product> product = service.getAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = service.getProductById(id);
        if(product!=null)
            return new ResponseEntity<>(product, HttpStatus.OK);

        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProduct(@PathVariable Long productId){
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }


//    @PostMapping("/product")
//    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
//        Product savedProduct = null;
//        try {
//            savedProduct = service.addProduct(product, imageFile);
//            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart("imageFile") MultipartFile imageFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(productJson, Product.class);

        Product savedProduct = service.addProduct(product, imageFile);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

//    @PutMapping("product/{id}")
//    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
//        Product product1 = null;
//
//        try{
//            product1 = service.updateProduct(id,product,imageFile);
//            return new ResponseEntity<>("Updated Successfullt", HttpStatus.OK);
//        }
//        catch (IOException e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestPart("product") String productJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);

            service.updateProduct(id, product, imageFile);

            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Product product = service.getProductById(id);
        if(product!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Deletion Failed", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
            List<Product> product = service.searchProduct(keyword);
            return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
