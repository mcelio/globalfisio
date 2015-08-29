/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.dao;

import br.com.generico.to.Agenda;
import br.com.generico.to.Guia;
import br.com.generico.to.Pessoa;
import br.com.generico.to.Sessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class AgendaDao {

    private DateFormat formataDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     *
     *
     * @param data
     * @return
     * @throws Exception
     */
    public List<Agenda> agendaDiaria(String data) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Agenda> lista = new ArrayList<Agenda>();
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("select g.id, g.indice, g.data, g.hora, g.descricao, p.nome, p.cpf, s.convenio from guia g ");
            sql.append("inner join sessao s on (s.id = g.id) ");
            sql.append("inner join pessoa p on (s.cliente_id = p.id) ");
            sql.append("where to_date(g.data, 'DD/MM/YYYY') = to_date('" + data + "', 'DD/MM/YYYY') ");
            sql.append("ORDER BY to_timestamp(g.data || ' ' || g.hora, 'DD/MM/YYYY HH24:MI')");

            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();            
            while (rs.next()) {
                Agenda agenda = new Agenda();
                Pessoa pessoa = new Pessoa();
                Sessao sessao = new Sessao();
                Guia guia = new Guia();
                
                guia.setId(rs.getLong("id"));
                guia.setIndice(rs.getInt("indice"));
                guia.setDescricao(rs.getString("descricao"));                                
                
                if(rs.getString("hora") != null && rs.getString("data") != null){
                    agenda.setData(formataDataHora.parse(rs.getString("data") + " " + rs.getString("hora")));
                } else if(rs.getString("data") != null){
                    agenda.setData(formataData.parse(rs.getString("data")));
                }                
                
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));                
                sessao.setConvenio(rs.getString("convenio"));
                agenda.setPessoa(pessoa);
                agenda.setSessao(sessao);
                agenda.setGuia(guia);
                lista.add(agenda);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
            connection.close();
        }
        return lista;
    }

    public void atualiza(Guia guia) throws Exception{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE guia set data = ?, hora  = ?, descricao = ? ");
            sql.append("WHERE id = ? and indice = ?");            
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, guia.getData());
            ps.setString(2, guia.getHora());
            ps.setString(3, guia.getDescricao());
            ps.setLong(4, guia.getId());
            ps.setLong(5, guia.getIndice());
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            ps.close();
            connection.close();
        }
    }
}
