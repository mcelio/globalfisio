/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.to;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class Sessao {
    
    private Long id;
    private Date data;
    private Pessoa pessoa;
    private String diagnostico;
    private String exame;
    private String convenio;
    private List<Guia> guias;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Guia> getGuias() {
        return guias;
    }

    public void setGuias(List<Guia> guias) {
        this.guias = guias;
    }
    
    
}
