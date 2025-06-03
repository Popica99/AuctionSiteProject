package sdacademy.auctionsiteproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdacademy.auctionsiteproject.entity.Category;
import sdacademy.auctionsiteproject.repository.CategoryRepository;
import sdacademy.auctionsiteproject.repository.UserRepository;

import java.security.PublicKey;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public Category createCategory(Category category)
    {
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
                orElseThrow(() -> new RuntimeException("Category with name " + name + " does not exists!"));
    }

    public String deleteCategoryByName(String name)
    {
        categoryRepository.delete(getCategoryByName(name));
        return "Category was deleted successfully!";
    }
}
