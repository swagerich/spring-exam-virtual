package com.erich.exam.services.impl;

import com.erich.exam.dto.EmailCustomDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;

    public void sendEmailRegister(EmailCustomDto customDto,String username) {


        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mensaje, true);
            customDto.getToEmails().forEach(d -> {
                try {
                    helper.addTo(d);
                } catch (MessagingException e) {
                    log.error("No se pudo agregar emails!");
                    throw new RuntimeException(e);
                }
            });

            helper.setSubject("Registro Exitoso!");
            String messageText = """
                     <html>
                      <body>
                        <h1>¡Registro exitoso!</h1>
                        <p>Hola %s</p>
                        <p>Gracias por registrarte en nuestro sitio web Examenes Virtual.</p>
                        <p>Si tienes alguna pregunta o problema, por favor no dudes en contactarme.</p>
                        <p>¡Gracias de nuevo y que tengas un buen día!</p>
                        <img src="cid:code">
                      </body>
                    </html>
                    """.formatted(username);
            helper.setText(messageText, true);
            FileSystemResource resource = new FileSystemResource(new File("src/main/resources/static/dev.jpg"));
            helper.addInline("code",resource);
        } catch (MessagingException e) {
            log.error("Nose pudo enviar el correo!");
            throw new RuntimeException(e);
        }
        javaMailSender.send(mensaje);
    }

}



