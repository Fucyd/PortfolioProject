package pl.michalski.PortfolioProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.michalski.PortfolioProject.dto.NewProductDto;
import pl.michalski.PortfolioProject.service.ProductService;

import javax.validation.Valid;


@Controller
@RequestMapping("products")
public class ProductController {
    // pobieranie produktow w oknie glownym
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("newProduct")
    public NewProductDto newProductDto() {
        return new NewProductDto();
    }

    @GetMapping("/all")
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView("all-products");
        modelAndView.addObject("allProducts", productService.getAllProducts());
        return modelAndView;
    }

    // szukanie produktu po nazwie
    // dodawanie produktu
    @GetMapping("/")
    public ModelAndView showNewProductForm(Model model) {
        ModelAndView modelAndView = new ModelAndView("new-product-form");
//        NewProductDto newProductDto = new NewProductDto();
//        modelAndView.addObject("newProduct", newProductDto);
        return modelAndView;
    }

    @PostMapping("/")
    public String saveNewProduct(@ModelAttribute("newProduct") @Valid NewProductDto newProductDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-product-form";
        }
        productService.addNewProduct(newProductDto);
        return "redirect:/products/all";
    }





    @GetMapping("/category/{category}")
    public String showAllProductsByCategory(@PathVariable("category") String category, Model model) {
        model.addAttribute("productsByCategory", productService.findProductsByCategory(category));
        model.addAttribute("category", getCategory(category));
        return "products_by_category";
    }

    private String getCategory(String c) {
        if (c.equals("ram")) return "Pamięć RAM";
        if (c.equals("laptops")) return "Laptopy";
        if (c.equals("disc")) return "Dyski";
        if (c.equals("graphicscards")) return "Karty Graficzne";
        if (c.equals("processors")) return "Procesory";
        return "Wszystkie produkty";
    }

}
