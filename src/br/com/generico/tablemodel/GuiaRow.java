/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.tablemodel;

import java.util.Date;

/**
 *
 * @author Marcos
 */
public class GuiaRow {
    
    private Boolean selecionado;
    private Integer guia;    
    private Integer indice;    
    private String descricao;    
    private Boolean feito;
    private Date data;
    private Date hora;

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Integer getGuia() {
        return guia;
    }

    public void setGuia(Integer guia) {
        this.guia = guia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFeito() {
        return feito;
    }

    public void setFeito(Boolean feito) {
        this.feito = feito;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }
    
}
