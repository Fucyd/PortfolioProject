package pl.michalski.PortfolioProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.michalski.PortfolioProject.dto.NewProductDto;
import pl.michalski.PortfolioProject.service.ProductService;

import javax.validation.Valid;


@Controller
@RequestMapping("products")
public class ProductController {
    // pobieranie produktow w oknie glownym
    @Autowired
    private ProductService productService;

    @ModelAttribute("newProduct")
    public NewProductDto newProductDto(){
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
    @GetMapping("/add")
    public ModelAndView showNewProductForm(Model model) {
        ModelAndView modelAndView = new ModelAndView("new-product-form");
//        NewProductDto newProductDto = new NewProductDto();
//        modelAndView.addObject("newProduct", newProductDto);
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveNewProduct(@ModelAttribute("newProduct") @Valid NewProductDto newProductDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "new-product-form";
        }
            productService.addNewProduct(newProductDto);
        return "redirect:/products/all";
    }

}
