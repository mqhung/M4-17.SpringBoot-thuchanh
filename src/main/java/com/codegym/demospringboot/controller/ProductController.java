package com.codegym.demospringboot.controller;

import com.codegym.demospringboot.model.Category;
import com.codegym.demospringboot.model.Product;
import com.codegym.demospringboot.service.category.ICategoryService;
import com.codegym.demospringboot.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("listCategory")
    public Iterable<Category> showAll(){
        return categoryService.findAll();
    }

    //show list
    @GetMapping()
    public ModelAndView showAllProducts(@PageableDefault (size = 3) Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("productList",products);
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }

    //create
    @GetMapping("create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("create", "product", new Product());
        return  modelAndView;
    }
    //edit
    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute Product product){
        product.setId(id);
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }
    //delete
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam Long id){
        productService.delete(id);
        return new ModelAndView("redirect:/products");
    }
    //search product
    @PostMapping("/search")
    public ModelAndView searchProductByName(@RequestParam String name, @PageableDefault (size = 3) Pageable pageable) {
        name = "%" + name + "%";
        Page<Product> products = productService.findByProductName(name, pageable);
        if (products.getSize() == 0) return new ModelAndView("error-404");
        ModelAndView modelAndView=new ModelAndView("home");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("productList",products);
        return modelAndView;
    }

    //search category
    @PostMapping("/searchCate")
    public ModelAndView searchProductByCategory(@RequestParam Long id, @PageableDefault (size = 3) Pageable pageable) {
        Page<Product> products = productService.findByCategoryName(id, pageable);
        if (products.getSize() == 0) return new ModelAndView("error-404");
        else return new ModelAndView("home", "productList", products);
    }
}
