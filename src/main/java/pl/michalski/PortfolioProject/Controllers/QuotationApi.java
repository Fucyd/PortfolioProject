package pl.michalski.PortfolioProject.Controllers;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.*;
import pl.michalski.PortfolioProject.Entities.Quotation;
import pl.michalski.PortfolioProject.Entities.QuotationDAO;

import java.io.Serializable;
import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationApi {
    private QuotationDAO quotationDAO = new QuotationDAO();
//    List<Quotation> quotations;

//    private QuotationDAO quotationDAO;


//    private List<Quotation> quotations;
//    public QuotationApi() {
//        quotations = new ArrayList<>();
//        quotations.add(new Quotation("Peter Lipperty", "Życia nie można wybrać, ale mozna coś z niego zrobić"));
//        quotations.add(new Quotation("Isaac Newton", "ludzie budują za dużo murów, a za mało mostów"));
//    }

    @GetMapping("/api/{id}")
    public Quotation getQotation(@RequestParam Long id){
        return quotationDAO.getQuotation(id);
    }

//    @PostMapping("/api")
//    public void addQuotation(@RequestBody Quotation quotation) {
//        quotationDAO.saveQuotation(quotation);
//    }

//    @DeleteMapping("/api")
//    public void deleteQuotation(@RequestParam int index){
//        quotations.remove(index);
//    }

}
