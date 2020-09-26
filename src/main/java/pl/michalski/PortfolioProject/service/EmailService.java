package pl.michalski.PortfolioProject.service;

public interface EmailService {
     void sendSimpleMessage(String to, String title, String content);
}
