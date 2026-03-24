package model;

import br.com.proagende.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;


public class Agendamento {

    //Atributos
    private int idAgendamento;
    private int idServico;
    private int idCliente;
    private LocalDateTime dataHora;
    private String status;
    
    //Construtor
    public Agendamento(int idAgendamento, int idServico, int idCliente, LocalDateTime dataHora, String status) {
        this.idAgendamento = idAgendamento;
        this.idServico = idServico;
        this.idCliente = idCliente;
        this.dataHora = dataHora;
        this.status = status;
    }
    
    
    //Getters e Setters
    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
  
}
