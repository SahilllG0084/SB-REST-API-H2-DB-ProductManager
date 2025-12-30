package com.cjc.app.service;

import java.util.List;
import com.cjc.app.entity.Product;

public interface ProductService {

   Product saveProduct(Product product);

   Product getProductById(int id);

   List<Product> getAllProducts();

   List<Product> getProductsInXml();

   String deleteProduct(int id);

   Product updateProduct(int id, Product product);

   Product editProduct(int id, Product product);

   List<Product> getProductsByPagination(int pageNumber, int pageSize);

   List<Product> getProductsSortedByPrice(String direction);

   List<Product> getProductsByName(String name);

   List<Product> getProductsByFilter(String category, Double minPrice, Double maxPrice);
}
