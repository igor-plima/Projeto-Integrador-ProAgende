package com.mycompany.projetointegrador;

import java.time.LocalDateTime;
import model.Agendamento;
import model.Cliente;
import model.Servico;

public class ProjetoIntegrador {

    public static void main(String[] args) {

        //Criando um cliente
        Cliente cliente = new Cliente(1,"João","Joao123@hotmail.com","11913451255" );

        //Criando um serviço
        Servico servico = new Servico(1,1,"Corte de Cabelo","Corte masculino padrão",50.0,45);
        
        //Criando um agendamento
        Agendamento agendamento = new Agendamento(1,servico.getIdServico(),cliente.getIdCliente(),LocalDateTime.now(),"Confirmado");

        //Protótipo
        System.out.println("--- AGENDAMENTO CRIADO ---");
        System.out.println("Cliente " + cliente.getNome());
        System.out.println("Serviço " + servico.getNome());
        System.out.println("Data/Hora " + agendamento.getDataHora());
        System.out.println(" Status " + agendamento.getStatus());

    }
}
