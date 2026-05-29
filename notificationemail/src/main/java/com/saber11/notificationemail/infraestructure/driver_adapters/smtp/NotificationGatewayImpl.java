package com.saber11.notificationemail.infraestructure.driver_adapters.smtp;
import com.saber11.notificationemail.domain.model.Notification;
import com.saber11.notificationemail.domain.model.gateway.NotificationGateway;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class NotificationGatewayImpl implements NotificationGateway {
    private final JavaMailSender javaMailSender;

    @Override

    public void sendEmail(Notification notification) {

        try {

            MimeMessage mimeMessage =
                    javaMailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true);

            helper.setTo(notification.getTo());

            helper.setSubject(notification.getSubject());
            String html = """
                    <!DOCTYPE html>
                    <html lang="es">
                    
                    <head>
                    <meta charset="UTF-8">
                    <title>Saber 11</title>
                    </head>
                    
                    <body style="
                        margin:0;
                        padding:0;
                        background-color:#d8eef0;
                        font-family:Arial,sans-serif;
                    ">
                    
                    <table width="100%%" cellpadding="0" cellspacing="0">
                    <tr>
                    <td align="center">
                    
                    <table width="600" cellpadding="0" cellspacing="0"
                    style="
                        background:white;
                        margin:40px auto;
                        border-radius:12px;
                        overflow:hidden;
                        box-shadow:0 4px 15px rgba(0,0,0,0.1);
                    ">
                    
                        <!-- HEADER -->
                        <tr>
                            <td style="
                                background:#1e88e5;
                                padding:40px;
                                text-align:center;
                                color:white;
                            ">
                    
                                <h1 style="
                                    margin:0;
                                    font-size:38px;
                                    letter-spacing:2px;
                                ">
                                    SABER 11
                                </h1>
                    
                                <p style="
                                    margin-top:10px;
                                    font-size:16px;
                                    opacity:0.9;
                                ">
                                    Plataforma de Simulacros Académicos
                                </p>
                    
                            </td>
                        </tr>
                    
                        <!-- IMAGE -->
                        <tr>
                            <td align="center"
                            style="
                                background:#42a5f5;
                                padding:30px;
                            ">
                    
                                <img
                                src="https://cdn-icons-png.flaticon.com/512/3135/3135755.png"
                                width="120"
                                alt="student">
                    
                            </td>
                        </tr>
                    
                        <!-- CONTENT -->
                        <tr>
                            <td style="
                                padding:50px;
                                text-align:center;
                            ">
                    
                                <h2 style="
                                    color:#333;
                                    font-size:34px;
                                    margin-bottom:20px;
                                ">
                                    Resultado de Simulacro
                                </h2>
                    
                                <p style="
                                    color:#666;
                                    font-size:16px;
                                    line-height:1.8;
                                ">
                                    %s
                                </p>
                    
                                <!-- BUTTON -->
                                <div style="margin-top:40px;">
                    
                                    <a href="#"
                                    style="
                                        background:#f9a825;
                                        color:white;
                                        text-decoration:none;
                                        padding:15px 35px;
                                        border-radius:6px;
                                        font-size:16px;
                                        font-weight:bold;
                                        display:inline-block;
                                    ">
                                        Ver Resultados
                                    </a>
                    
                                </div>
                    
                            </td>
                        </tr>
                    
                        <!-- FOOTER -->
                        <tr>
                            <td style="
                                background:#f5f5f5;
                                padding:30px;
                                text-align:center;
                                color:#777;
                                font-size:13px;
                            ">
                    
                                Sigue preparándote para alcanzar
                                un excelente puntaje en las Pruebas Saber 11.
                    
                                <br><br>
                    
                                © 2026 Plataforma Saber 11
                    
                            </td>
                        </tr>
                    
                    </table>
                    
                    </td>
                    </tr>
                    </table>
                    
                    </body>
                    </html>
                    """.formatted(notification.getBody());
            helper.setText(html, true);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error enviando correo"
            );
        }
    }}