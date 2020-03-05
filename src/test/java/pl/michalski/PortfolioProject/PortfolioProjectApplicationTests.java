package pl.michalski.PortfolioProject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.michalski.PortfolioProject.Entities.Quotation;
import pl.michalski.PortfolioProject.Entities.QuotationDAO;

@SpringBootTest
class PortfolioProjectApplicationTests {
	private QuotationDAO quotationDAO;

	@Test
	void contextLoads() {
	}

	@Test
	void insert(){
		quotationDAO = new QuotationDAO();
		Quotation quotation = new Quotation();
		quotation.setId(1);
		quotation.setAuthor("Ja sam");
		quotation.setContent("Jestem tak mądry, że aż głupi");

		quotationDAO.saveQuotation(quotation);
	}

}
