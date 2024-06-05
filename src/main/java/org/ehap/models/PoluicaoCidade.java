package org.ehap.models;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"cidade", "regiao", "ano", "qualidadeAr", "poluicaoAgua", "entidadeId"})
public class PoluicaoCidade {

    private String cidade;
    private String regiao;
    private Integer ano;
    private double qualidadeAr;
    private double poluicaoAgua;
    private Integer entidadeId;

    public PoluicaoCidade() {
    }

    public PoluicaoCidade(String cidade, String regiao, Integer ano, double qualidadeAr, double poluicaoAgua, Integer entidadeId) {
        this.cidade = cidade;
        this.regiao = regiao;
        this.ano = ano;
        this.qualidadeAr = qualidadeAr;
        this.poluicaoAgua = poluicaoAgua;
        this.entidadeId = entidadeId;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public double getQualidadeAr() {
        return qualidadeAr;
    }

    public void setQualidadeAr(double qualidadeAr) {
        this.qualidadeAr = qualidadeAr;
    }

    public double getPoluicaoAgua() {
        return poluicaoAgua;
    }

    public void setPoluicaoAgua(double poluicaoAgua) {
        this.poluicaoAgua = poluicaoAgua;
    }

    public Integer getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Integer entidadeId) {
        this.entidadeId = entidadeId;
    }
}
