package com.cjc.app.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/v2")
public class ProductController {
     
	private ProductService prodserv;

	public ProductController(ProductService prodserv) {
		super();
		this.prodserv = prodserv;
	}
	
	@PostMapping(value = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
		                                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		Product savedProduct = prodserv.saveProduct(product);
		
		if(savedProduct != null)
		{
			return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/products/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id)
	{
		Product dbproduct = prodserv.getProductById(id);
		
	    if(dbproduct != null)
	    {
	      return new ResponseEntity<Product>(dbproduct, HttpStatus.OK);
	    }
	    
	      return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/products")
	public  ResponseEntity<List<Product>> getProducts()
	{
		List<Product> allProducts = prodserv.getAllProducts();
		
		if(!allProducts.isEmpty())
		{
			return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
		}
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/products-xml", produces = {MediaType.APPLICATION_XML_VALUE })
	public Products getProductsXml()
	{
		List<Product> xmllist = prodserv.getProductsInXml();
		
		Products product = new Products();
		
		product.setProductlist(xmllist);
		
		return product;
	}
	
	@DeleteMapping(value = "/products/{id}")
	public String deleteProduct(@PathVariable("id") int id)
	{
		  String msg = prodserv.deleteProduct(id);
		  
		  return msg;
	}
	
	@PutMapping(value = "/products/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product)
	{
		Product updatedProduct = prodserv.updateProduct(id , product);
		
		return updatedProduct;
	}
	
	@PatchMapping(value = "/products/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Product editProduct(@PathVariable("id") int id, @RequestBody Product product)
	{
		 Product editedproduct = prodserv.editProduct(id , product);
		 
		 return editedproduct;
	}
	
	//Pagination :-
	@GetMapping(value = "/products/paging", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Product> getProductsByPagination(@RequestParam(defaultValue = "0") int pageNumber,
			                                     @RequestParam(defaultValue = "10") int pageSize)
	{
		List<Product> pagination = prodserv.getProductsByPagination(pageNumber , pageSize);
		
		return pagination;
	}
	
	//Sorting :- By Price -> Ascending & Descending Order.
	@GetMapping(value = "/products/sortByPrice", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Product> getProductsBySorting(@RequestParam(defaultValue = "asc") String direction)
	{
		  return prodserv.getProductsSortedByPrice(direction);
	}
	
	//Searching :- ByName
	@GetMapping(value = "/products/searchByName/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Product> getProductByName(@PathVariable("name") String name)
	{
		return prodserv.getProductsByName(name);
	}
	
	//Filtering :- Products By Category And Price Range(minPrice, maxPrice)
	@GetMapping(value = "/products/filter", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Product> getProductsByFiltering(@RequestParam(required = false) String category,
			                                    @RequestParam(required = false) Double minPrice,
			                                    @RequestParam(required = false) Double maxPrice)
	{		
		 return prodserv.getProductsByFilter(category, minPrice, maxPrice);
	}
}
