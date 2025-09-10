package com.company.ecommerce.mvc;

import com.company.ecommerce.dto.request.CategoryRequestDto;
import com.company.ecommerce.dto.request.ProductRequestDto;
import com.company.ecommerce.dto.response.CategoryResponseDto;
import com.company.ecommerce.dto.response.ProductMvcResponseDto;
import com.company.ecommerce.dto.response.ProductResponseDto;
import com.company.ecommerce.dto.response.SellerResponseDto;
import com.company.ecommerce.service.CategoryService;
import com.company.ecommerce.service.ProductService;
import com.company.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductMvcController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SellerService sellerService;

    @GetMapping("/get-all")
    public String products(Model model){
        List<ProductMvcResponseDto> content = productService.getAll().getContent();
        List<CategoryResponseDto> categories = categoryService.getAllCategory().getContent();
        model.addAttribute("products", content);
        model.addAttribute("categories", categories);
        return "products/products :: content";
    }

    @GetMapping("/create")
    public String createPage(Model model){
        List<SellerResponseDto> sellers = sellerService.getAll().getContent();
        List<CategoryResponseDto> categories = categoryService.getAllCategory().getContent();
        model.addAttribute("sellers", sellers);
        model.addAttribute("categories", categories);
        return "products/product-form :: form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ProductRequestDto dto, Model model){
        productService.createProduct(dto);
        List<ProductMvcResponseDto> content = productService.getAll().getContent();
        List<CategoryResponseDto> categories = categoryService.getAllCategory().getContent();
        model.addAttribute("products", content);
        model.addAttribute("categories", categories);
        return "products/products :: content";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model){
        ProductResponseDto content = productService.getProductById(id).getContent();
        model.addAttribute("product", content);
        return "products/product-edit :: form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute ProductRequestDto dto, Model model){
        productService.updateProduct(dto, id);
        List<ProductMvcResponseDto> content = productService.getAll().getContent();
        List<CategoryResponseDto> categories = categoryService.getAllCategory().getContent();
        model.addAttribute("products", content);
        model.addAttribute("categories", categories);
        return "products/products :: content";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    // Category

    @GetMapping("/category/create")
    public String createPage(){
        return "products/category-form :: form";
    }

    @PostMapping("/category/create")
    public String create(@ModelAttribute CategoryRequestDto dto, Model model){
        categoryService.createCategory(dto);
        List<ProductMvcResponseDto> content = productService.getAll().getContent();
        List<CategoryResponseDto> categories = categoryService.getAllCategory().getContent();
        model.addAttribute("products", content);
        model.addAttribute("categories", categories);
        return "products/products :: content";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, value = "search") String search, Model model){
        List<ProductMvcResponseDto> content = productService.search(search).getContent();
        model.addAttribute("products", content);
        return "products/products :: product-table";
    }
}
