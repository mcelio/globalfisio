/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.to;

import java.util.Date;

/**
 *
 * @author Marcos
 */
public class Agenda {
    private Guia guia;
    private Date data;
    private Pessoa pessoa;
    private Sessao sessao;

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
        
    
}
