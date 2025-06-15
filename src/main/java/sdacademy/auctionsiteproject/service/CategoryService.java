package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.auctionsiteproject.entity.Category;
import sdacademy.auctionsiteproject.exceptions.CategoryAlreadyExistsException;
import sdacademy.auctionsiteproject.exceptions.CategoryNotFoundException;
import sdacademy.auctionsiteproject.repository.CategoryRepository;

import java.security.PublicKey;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public Category createCategory(Category category)
    {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException("Category already exists!");
        }

        Category newCategory = new Category();

        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());

        return categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategories()
    {

        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name)
    {
        return categoryRepository.findByName(name).
                orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }

    public String deleteCategoryByName(String name)
    {
        categoryRepository.delete(getCategoryByName(name));
        return "Category was deleted successfully!";
    }
}
