package br.edu.unifacisa.atendimento.model;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private StatusConsulta status = StatusConsulta.AGENDADA;
    private String observacoes;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora) {
        setPaciente(paciente);
        setMedico(medico);
        setDataHora(dataHora);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente é obrigatório.");
        }
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("Médico é obrigatório.");
        }
        this.medico = medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora são obrigatórias.");
        }
        this.dataHora = dataHora;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Consulta #" + id + " " + dataHora + " [" + status + "] paciente=" + paciente.getNome()
                + " médico=" + medico.getNome() + (observacoes == null ? "" : " obs=" + observacoes);
    }
}
