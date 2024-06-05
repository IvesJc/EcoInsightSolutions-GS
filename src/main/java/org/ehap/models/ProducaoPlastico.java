package org.ehap.models;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"ano", "producaoAnualPlastico", "participacaoEmissaoOceanos",
        "participacaoReciclagemRegional", "participacaoQueimaRegional", "participacaoLixoMalGerido",
        "participacaoAterrosRegional", "lixoMalGeridoPerCapita", "entidadeId"})
public class ProducaoPlastico {

    private Integer ano;
    private double producaoAnualPlastico;
    private double participacaoEmissaoOceanos;
    private double participacaoReciclagemRegional;
    private double participacaoQueimaRegional;
    private double participacaoLixoMalGerido;
    private double participacaoAterrosRegional;
    private double lixoMalGeridoPerCapita;
    private Integer entidadeId;

    public ProducaoPlastico() {
    }

    public ProducaoPlastico(Integer ano, double producaoAnualPlastico, double participacaoEmissaoOceanos, double participacaoReciclagemRegional, double participacaoQueimaRegional, double participacaoLixoMalGerido, double participacaoAterrosRegional, double lixoMalGeridoPerCapita, Integer entidadeId) {
        this.ano = ano;
        this.producaoAnualPlastico = producaoAnualPlastico;
        this.participacaoEmissaoOceanos = participacaoEmissaoOceanos;
        this.participacaoReciclagemRegional = participacaoReciclagemRegional;
        this.participacaoQueimaRegional = participacaoQueimaRegional;
        this.participacaoLixoMalGerido = participacaoLixoMalGerido;
        this.participacaoAterrosRegional = participacaoAterrosRegional;
        this.lixoMalGeridoPerCapita = lixoMalGeridoPerCapita;
        this.entidadeId = entidadeId;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public double getProducaoAnualPlastico() {
        return producaoAnualPlastico;
    }

    public void setProducaoAnualPlastico(double producaoAnualPlastico) {
        this.producaoAnualPlastico = producaoAnualPlastico;
    }

    public double getParticipacaoEmissaoOceanos() {
        return participacaoEmissaoOceanos;
    }

    public void setParticipacaoEmissaoOceanos(double participacaoEmissaoOceanos) {
        this.participacaoEmissaoOceanos = participacaoEmissaoOceanos;
    }

    public double getParticipacaoReciclagemRegional() {
        return participacaoReciclagemRegional;
    }

    public void setParticipacaoReciclagemRegional(double participacaoReciclagemRegional) {
        this.participacaoReciclagemRegional = participacaoReciclagemRegional;
    }

    public double getParticipacaoQueimaRegional() {
        return participacaoQueimaRegional;
    }

    public void setParticipacaoQueimaRegional(double participacaoQueimaRegional) {
        this.participacaoQueimaRegional = participacaoQueimaRegional;
    }

    public double getParticipacaoLixoMalGerido() {
        return participacaoLixoMalGerido;
    }

    public void setParticipacaoLixoMalGerido(double participacaoLixoMalGerido) {
        this.participacaoLixoMalGerido = participacaoLixoMalGerido;
    }

    public double getParticipacaoAterrosRegional() {
        return participacaoAterrosRegional;
    }

    public void setParticipacaoAterrosRegional(double participacaoAterrosRegional) {
        this.participacaoAterrosRegional = participacaoAterrosRegional;
    }

    public double getLixoMalGeridoPerCapita() {
        return lixoMalGeridoPerCapita;
    }

    public void setLixoMalGeridoPerCapita(double lixoMalGeridoPerCapita) {
        this.lixoMalGeridoPerCapita = lixoMalGeridoPerCapita;
    }

    public Integer getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Integer entidadeId) {
        this.entidadeId = entidadeId;
    }
}
