package com.example.unza_library.config;

import com.example.unza_library.entity.Book;
import com.example.unza_library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class EmailSender {


    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendReservation(User user, Book book){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reservation confirmation");
        mailMessage.setText(
                "Your reservation for the book '"+ book.getBookName()+"' has been created. "
                +"Please wait for the issue email to confirm your borrowing"
        );

        mailMessage.setSentDate(new Date());

        javaMailSender.send(mailMessage);
    }

    public void sendRejectionReservation(User user, Book book){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reservation rejection");
        mailMessage.setText(
                "Your reservation for the book '"+ book.getBookName()+"' has been rejected. Your" +
                        " borrowing limit has reached."
                        +"Please return one book for you to borrow again"
        );

        mailMessage.setSentDate(new Date());

        javaMailSender.send(mailMessage);
    }
    public void sendRejectedReservation(User user, Book book){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reservation rejection");
        mailMessage.setText(
                "Your reservation for the book '"+ book.getBookName()+"' has been rejected. You" +
                        " can not borrow the same book twice at the same time."
                        +"Please return one book for you to borrow again"
        );

        mailMessage.setSentDate(new Date());

        javaMailSender.send(mailMessage);
    }

    public void sendApprovedReservation(User user, Book book){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reservation confirmation");
        mailMessage.setText(
                "Your reservation for the book '"+ book.getBookName()+"' has been approved. "
                        +"Please pass through the issue desk to collect your book"
        );

        mailMessage.setSentDate(new Date());

        javaMailSender.send(mailMessage);
    }

    public void sendCollection(User user, Book book){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Book collected");
        mailMessage.setText(
                "Your book '"+ book.getBookName()+"' has been collected. "
                        +"The book was collected on " + new Date()+". You have 14 days to return"+
                        ", thereafter there will be a penalty of K2 everyday"
        );

        mailMessage.setSentDate(new Date());

        javaMailSender.send(mailMessage);
    }


}
