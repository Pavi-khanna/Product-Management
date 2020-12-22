package com.example.website.service;

import com.example.website.dao.ProductRepository;
import com.example.website.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //create
    public Product createProduct(Product product){
        return productRepository.save(new Product(product.getId(),product.getName(),product.getPrice(),product.isAvailable(),product.getCategory()));
    }

    //retrieve all
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    //retrieve by name
    public Product getByName(String name){
        return productRepository.findByName(name);
    }

    //update
    public Product update(String name, double price, boolean available){
        Product product = productRepository.findByName(name);
        product.setName(name);
        product.setPrice(price);
        product.setAvailable(available);
        return productRepository.save(product);
    }

    //public List<Product> sortCategory(String category){
    //    Sort sort= new Sort(Sort.Direction.ASC,"category");
    //    List<Product> products = productRepository.findAllByCategory(category);
    //    return null;
    //}

    public List<Product> sortPrice(double price){
        //List<Product> products = productRepository.findAllByPriceAfter(price);
        //List<Product> products = productRepository.findAllByPriceAfter(price);
        List<Product> products = getAll();
        List<Product> ans = products.stream()
                .filter(p->p.getPrice()>price)
                .collect(Collectors.toList());
        return ans;
    }

    //delete by name
    public void delete(long id){
        productRepository.deleteById(id);
    }

    //delete
    public void deleteAll(){
        productRepository.deleteAll();
    }

    //sort by category
    public List<Product> getCategory(Product category) {
        //ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("category", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        //Example<Product> productExample = Example.of(category,exampleMatcher);
        //return productRepository.findAll(productExample);

        List<Product> products = getAll();
        List<Product> ans = products.stream()
               .filter(p-> p.getCategory().equals(category.getCategory()))
               .collect(Collectors.toList());
        return ans;
    }

    public List<Product> getNotAvailableProducts() {
        List<Product> products = getAll();
        List<Product> ans = products.stream()
                .filter(p-> !p.isAvailable())
                .collect(Collectors.toList());
        return ans;
    }
}
