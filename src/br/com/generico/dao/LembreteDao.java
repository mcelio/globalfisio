/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.dao;

import br.com.generico.to.Lembrete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class LembreteDao {
    
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    
    public List<Lembrete> pesquisa(Lembrete lembrete, String dataDe, String dataAte) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Lembrete> lista = null;
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM lembrete");
            sql.append(" WHERE");            
            if (dataDe != null && dataAte != null) {
                sql.append(" to_date(data, 'dd/MM/yyyy') between to_date('" + dataDe + "', 'dd/MM/yyyy') and to_date('" + dataAte + "', 'dd/MM/yyyy')");
            }
            sql.append(" ORDER BY to_date(data, 'dd/MM/yyyy') DESC");
            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Lembrete>();
            while (rs.next()) {
                Lembrete o = new Lembrete();
                o.setId(rs.getLong("id"));
                o.setDescricao(rs.getString("descricao"));
                o.setData(formataData.parse(rs.getString("data")));
                lista.add(o);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
            connection.close();
        }        
        return lista;
    }
    
    public List<Lembrete> listarAvisos() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Lembrete> lista = null;
        try {
            Calendar calDe = Calendar.getInstance();
            Calendar calAte = Calendar.getInstance();            
            calDe.roll(Calendar.DAY_OF_MONTH, -2);
            calAte.roll(Calendar.DAY_OF_MONTH, 2);
            
            String dataDe = formataData.format(calDe.getTime());
            String dataAte = formataData.format(calAte.getTime());
            
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM lembrete");
            sql.append(" WHERE");
            sql.append(" to_date(data, 'dd/MM/yyyy') between to_date('" + dataDe + "', 'dd/MM/yyyy') and to_date('" + dataAte + "', 'dd/MM/yyyy')");
            sql.append(" AND avisar = 1");
            sql.append(" ORDER BY to_date(data, 'dd/MM/yyyy') ASC");
            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Lembrete>();
            while (rs.next()) {
                Lembrete o = new Lembrete();
                o.setId(rs.getLong("id"));
                o.setDescricao(rs.getString("descricao"));
                o.setData(formataData.parse(rs.getString("data")));
                lista.add(o);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
            connection.close();
        }        
        return lista;
    }
    
    
    public void inserir(Lembrete lembrete) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String validacao = validaLembrete(lembrete);
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder insereSessaoSql = new StringBuilder();
            insereSessaoSql.append("INSERT INTO lembrete(");
            insereSessaoSql.append("id, descricao, data, avisar)");
            insereSessaoSql.append("VALUES (?, ?, ?, ?);");
            ps = connection.prepareStatement(insereSessaoSql.toString());
            if (validacao == null) {
                Long nextId = getSequenceNextVal();
                ps.setLong(1, nextId);
                ps.setString(2, lembrete.getDescricao().trim());
                ps.setString(3, formataData.format(lembrete.getData()));
                ps.setInt(4, lembrete.getAvisar());
                ps.executeUpdate();
                connection.commit();
           } else {
                throw new Exception(validacao);
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            ps.close();
            connection.close();
        }
    }
    
    
    public void atualiza(Lembrete lembrete) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String validacao = validaLembrete(lembrete);
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder insereSessaoSql = new StringBuilder();
            insereSessaoSql.append("UPDATE lembrete SET");
            insereSessaoSql.append(" data = ?, descricao = ?");
            insereSessaoSql.append(" WHERE id = ?;");
            ps = connection.prepareStatement(insereSessaoSql.toString());
            if (validacao == null) {
                ps.setString(1, formataData.format(lembrete.getData()));
                ps.setString(2, lembrete.getDescricao().trim());                
                ps.setLong(3, lembrete.getId());                
                ps.executeUpdate();
                connection.commit();
           } else {
                throw new Exception(validacao);
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            ps.close();
            connection.close();
        }
    }
        
    
    private Long getSequenceNextVal() throws Exception {
        Connection connection = null;
        try {
            String query = "select NEXTVAL('lembrete_seq')";
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            java.sql.Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                return rs.getLong(1);
            }
            stm.close();
            return -1L;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    public String validaLembrete(Lembrete lembrete) {
        StringBuilder retorno = new StringBuilder();
        if (lembrete.getDescricao() == null || lembrete.getDescricao().trim().equals("")) {
            retorno.append("Descrição deve ser preenchido.\n");
        }
        if (lembrete.getData() == null) {
            retorno.append("Data inválido.\n");
        }
        if (retorno.toString().equals("")) {
            return null;
        }
        return retorno.toString();
    }
    
}
