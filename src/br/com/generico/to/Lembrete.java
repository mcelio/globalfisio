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
public class Lembrete {
    
    public static final Integer AVISAR_SIM = 1;
    public static final Integer AVISAR_NAO = 0;
    
    private Long id;
    private String descricao;
    private Date data;
    private Integer avisar;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAvisar() {
        return avisar;
    }

    public void setAvisar(Integer avisar) {
        this.avisar = avisar;
    }
    
    
}
