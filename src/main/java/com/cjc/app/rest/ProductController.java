package com.cjc.app.rest;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cjc.app.entity.Product;
import com.cjc.app.entity.Products;
import com.cjc.app.service.ProductService;

@RestController
@RequestMapping(value = "/api")
public class ProductController {
     
	private ProductService prodserv;

	public ProductController(ProductService prodserv) {
		super();
		this.prodserv = prodserv;
	}
	
	@PostMapping(value = "/addproduct", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
		                                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Product addProduct(@RequestBody Product product)
	{
		Product savedProduct = prodserv.saveProduct(product);
		
		return savedProduct;
	}
	
	@GetMapping(value = "/getproduct/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Product getProductById(@PathVariable("id") int id)
	{
		Product dbproduct = prodserv.getProductById(id);
		
		return dbproduct;   
	}
	
	@GetMapping(value = "/getproducts")
	public List<Product> getProducts()
	{
		List<Product> allProducts = prodserv.getAllProducts();
		
		return allProducts;
	}
	
	@GetMapping(value = "/getproducts-xml", produces = {MediaType.APPLICATION_XML_VALUE })
	public Products getProductsXml()
	{
		List<Product> xmllist = prodserv.getProductsInXml();
		
		Products product = new Products();
		
		product.setProductlist(xmllist);
		
		return product;
	}
	
	@DeleteMapping(value = "/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") int id)
	{
		  String msg = prodserv.deleteProduct(id);
		  
		  return msg;
	}
	
	@PutMapping(value = "/updateproduct/{id}")
	public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product)
	{
		Product updatedProduct = prodserv.updateProduct(id , product);
		
		return updatedProduct;
	}
	
	@PatchMapping(value = "/editproduct/{id}")
	public Product editProduct(@PathVariable("id") int id, @RequestBody Product product)
	{
		 Product editedproduct = prodserv.editProduct(id , product);
		 
		 return editedproduct;
	}
	
	//Pagination :-	
	@GetMapping(value = "/getproducts/paging")
	public List<Product> getProductsByPagination(@RequestParam(defaultValue = "0") int pageNumber,
			                                     @RequestParam(defaultValue = "10") int pageSize)
	{
		List<Product> pagination = prodserv.getProductsByPagination(pageNumber , pageSize);
		
		return pagination;
	}
	
	//Sorting :- By Price -> Ascending & Descending Order.
	@GetMapping(value = "/getproducts/sortByPrice")
	public List<Product> getProductsBySorting(@RequestParam(defaultValue = "asc") String direction)
	{
		  return prodserv.getProductsSortedByPrice(direction);
	}
	
	//Searching :- ByName
	@GetMapping(value = "/getproducts/searchByName/{name}")
	public List<Product> getProductByName(@PathVariable("name") String name)
	{
		return prodserv.getProductsByName(name);
	}
	
	//Filtering Data By Category And Price Range(minPrice, maxPrice)
	@GetMapping(value = "/getproducts/filter")
	public List<Product> getProductsByFiltering(@RequestParam(required = false) String category,
			                                    @RequestParam(required = false) Double minPrice,
			                                    @RequestParam(required = false) Double maxPrice)
	{		
		 return prodserv.getProductsByFilter(category, minPrice, maxPrice);
	}
}
