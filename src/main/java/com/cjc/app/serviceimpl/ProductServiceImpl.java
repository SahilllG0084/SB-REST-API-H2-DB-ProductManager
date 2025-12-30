package com.cjc.app.serviceimpl;

import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.cjc.app.dao.ProductRepository;
import com.cjc.app.entity.Product;
import com.cjc.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    
	private ProductRepository prodrepo;

	public ProductServiceImpl(ProductRepository prodrepo) {
		super();
		this.prodrepo = prodrepo;
	}
	
	@Override
	public Product saveProduct(Product product) {
		
		Product dbproduct = prodrepo.save(product);
		
		return dbproduct;
	}
	
	@Override
	public Product getProductById(int id) {
		
		if(prodrepo.existsById(id))
		{
			Product dbproduct = prodrepo.findById(id).get();
			
			return dbproduct;
		}
		
		return null;
	}
	
	@Override
	public List<Product> getAllProducts() {
		
		List<Product> productlist = prodrepo.findAll();
		
		return productlist;
	}
	
	@Override
	public List<Product> getProductsInXml() {
		
		return prodrepo.findAll();
	}
	
	@Override
	public String deleteProduct(int id) {
		
		String msg = "";
		
		if(prodrepo.existsById(id)) {
			
			prodrepo.deleteById(id);
			
			return msg = "Product Deleted Successfully For A Gievn Id :"+id;
		}
				
		return msg = "Product Not Available For Given Id :"+id;
	}
	
	@Override
	public Product updateProduct(int id, Product product) {
		
		if(prodrepo.existsById(id))
		{
			Product dbproduct = prodrepo.findById(id).get();
			
			dbproduct.setId(product.getId());
			dbproduct.setName(product.getName());
			dbproduct.setBrandname(product.getBrandname());
			dbproduct.setCategory(product.getCategory());
			dbproduct.setStockquantity(product.getStockquantity());
			dbproduct.setPrice(product.getPrice());
			
			Product savedproduct = prodrepo.save(dbproduct);
			
			return savedproduct;
		}
		
		return null;
	}
	
	@Override
	public Product editProduct(int id, Product product) {
		
		if(prodrepo.existsById(id))
		{
			Product dbpr = prodrepo.findById(id).get();
			
			if(product.getId() != null)
			{
				dbpr.setId(product.getId());
			}
			
			if(product.getName() != null)
			{
				dbpr.setName(product.getName());
			}
			
			if(product.getBrandname() != null)
			{
				dbpr.setBrandname(product.getBrandname());
			}
			
			if(product.getCategory() != null)
			{
				dbpr.setCategory(product.getCategory());
			}
			
			if(product.getStockquantity() != null)
			{
				dbpr.setStockquantity(product.getStockquantity());
			}
			
			if(product.getPrice() != null)
			{
				dbpr.setPrice(product.getPrice());
			}
			
			return prodrepo.save(dbpr);
		}
		
		return null;
	}
	
	@Override
	public List<Product> getProductsByPagination(int pageNumber, int pageSize) {
		
		Pageable pages = PageRequest.of(pageNumber, pageSize);
		
		Page<Product> foundedprod = prodrepo.findAll(pages);
		
		if(foundedprod.hasContent()) {
			
			List<Product> content = foundedprod.getContent();
			
			return content;
		}
		
		return null;
	}

	@Override
	public List<Product> getProductsSortedByPrice(String direction) {
		
		Sort sort;
		
		if(direction != null && direction.equalsIgnoreCase("desc"))
		{
			 sort = Sort.by(Sort.Direction.DESC, "price");
		}
		else {
	         sort = Sort.by(Sort.Direction.ASC, "price");
		}
		
		return prodrepo.findAll(sort);
	}
	
	@Override
	public List<Product> getProductsByName(String name) {
		
		List<Product> prodslist = prodrepo.findByName(name);
		
		if(!prodslist.isEmpty())
		   return prodslist;
		else 
		   return Collections.emptyList();
	}
	
	@Override
	public List<Product> getProductsByFilter(String category, Double minPrice, Double maxPrice) {
		
		return prodrepo.filterProducts(category, minPrice, maxPrice);
	}
}
