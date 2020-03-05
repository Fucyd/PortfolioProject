package pl.michalski.PortfolioProject.Entities;

import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.HibernateTemplate;
import pl.michalski.PortfolioProject.Configuration.HibernateFactory;

import java.util.ArrayList;
import java.util.List;


public class QuotationDAO {
    HibernateFactory hibernateFactory = new HibernateFactory();

//    public void setTemplate(HibernateTemplate template) {
//        this.template = template;
//    }

    public void saveQuotation(Quotation quotation) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(quotation);
            session.beginTransaction().commit();
        } catch (Exception c) {
            System.out.println("SaveQuotation - coś sie popsuło");
        } finally {
            session.close();
        }

    }

    public Quotation getQuotation(Long id) {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Quotation quotation = null;
        try {
            quotation =  session.get(Quotation.class, id);
            session.getTransaction().commit();
        } catch (Exception c) {
            System.out.println("GetQuotation - coś sie popsuło");
        } finally {
            session.close();
        }
        return quotation;
    }

//    public List<Quotation> getAllQuotations(){
//        List<Quotation> quotationList = new ArrayList<>();
//        quotationList = template.loadAll(Quotation.class);
//        return quotationList;
//    }
}
