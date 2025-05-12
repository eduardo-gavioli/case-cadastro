package com.example.cadastro.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public NotificacaoService(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    public void enviarNotificacao(String mensagem) {
        rabbitTemplate.convertAndSend("fila_notificacao", mensagem);
    }
}