package sdacademy.auctionsiteproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.entity.Category;
import sdacademy.auctionsiteproject.exceptions.CategoryNotFoundException;
import sdacademy.auctionsiteproject.service.CategoryService;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        try{
            Category newCategory = categoryService.createCategory(category);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (CategoryNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategoryByName (@PathVariable String name)
    {
        try{
            Category categoryByName = categoryService.getCategoryByName(name);
            return new ResponseEntity<>(categoryByName, HttpStatus.OK);
        } catch (CategoryNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCategoryByName(@PathVariable String name)
    {
        try {
            String message = categoryService.deleteCategoryByName(name);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (CategoryNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}/auctions")
    public ResponseEntity<List<Auction>> getAuctionsByCategory(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        List<Auction> auctions = category.getAuctions();
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }
}
