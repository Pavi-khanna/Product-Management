package com.example.website.api;

import com.example.website.model.Product;
import com.example.website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //create a product
    @PostMapping("/createOne")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    //fetch all products
    @GetMapping("/getAll")
    public List<Product> getProducts(){
        return productService.getAll();
    }

    //create n products
    @PostMapping("/createNProducts")
    public String createNProducts(@RequestBody String[] names){
        for(int i=0;i< names.length;i++){
            String[] categories = {"Transport","Electronics","Furniture","Entertainment","Fitness","Sports"};
            Random random = new Random();
            HashSet<String> categorySet= new HashSet<>();

            for(int k=0;k<random.nextInt(6);k++){
                categorySet.add(categories[random.nextInt(5)]);
            }

            Product product = new Product(new Random().nextLong(),names[i],new Random().nextDouble(),new Random().nextBoolean(), categorySet);
            productService.createProduct(product);
        }
        return "Created "+names.length+" Products";
    }

    //sort by price
    @GetMapping("/getByPrice")
    public List<Product> getByPrice(@RequestBody double price){
        return productService.sortPrice(price);
    }

    //@PostMapping("/getCategory")
    //public List<Product> getByCategory(@RequestBody String category){
    //    return productService.sortCategory(category);
    //}

    //sort by category
    @GetMapping("/getByCategory")
    public List<Product> getCategory(@RequestBody Product category){
        return productService.getCategory(category);
    }

    //fetch non available products
    @GetMapping("/notAvailable")
    public List<Product> notAvailable(){
        return productService.getNotAvailableProducts();
    }

    //update a product
    @PutMapping("/updateOne")
    public Product update(@RequestBody Product product){
        return productService.update(product.getName(),product.getPrice(),product.isAvailable());
    }

    //delete a product
    @DeleteMapping("/deleteOne")
    public void delete(@RequestBody long id){
        productService.delete(id);
    }

    //delete all products
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        productService.deleteAll();
    }

}
