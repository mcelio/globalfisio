/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.dao;

import br.com.generico.to.Custo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CustoDao {
    
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    
    public List<Custo> pesquisa(Custo custo, String dataDe, String dataAte) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Custo> lista = null;
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM custo WHERE");
                
            boolean and = false;
            if (custo.getNome() != null && !custo.getNome().trim().equals("")) {                
                sql.append(" nome = '" + custo.getNome().trim() + "'");
                and = true;
            }    
              

            if (dataDe != null && dataAte != null) {
                if (and) {
                    sql.append(" and");
                }
                sql.append(" to_date(data, 'dd/MM/yyyy') between to_date('" + dataDe + "', 'dd/MM/yyyy') and to_date('" + dataAte + "', 'dd/MM/yyyy')");
            }

            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Custo>();
            while (rs.next()) {
                Custo o = new Custo();
                o.setId(rs.getLong("id"));
                o.setNome(rs.getString("nome"));
                o.setValor(rs.getDouble("valor"));
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
    
    
    public void inserir(Custo custo) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String validacao = validaCusto(custo);
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder insereSessaoSql = new StringBuilder();
            insereSessaoSql.append("INSERT INTO custo(");
            insereSessaoSql.append("id, data, descricao, valor, nome)");
            insereSessaoSql.append("VALUES (?, ?, ?, ?, ?);");
            ps = connection.prepareStatement(insereSessaoSql.toString());
            if (validacao == null) {
                Long nextId = getSequenceNextVal();
                ps.setLong(1, nextId);                
                ps.setString(2, formataData.format(custo.getData()));
                ps.setString(3, custo.getDescricao().trim());
                ps.setDouble(4, custo.getValor());
                ps.setString(5, custo.getNome().trim());                
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
    
    
    public void atualiza(Custo custo) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String validacao = validaCusto(custo);
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder insereSessaoSql = new StringBuilder();
            insereSessaoSql.append("UPDATE custo SET");
            insereSessaoSql.append(" data = ?, descricao = ?, valor = ?, nome = ?");
            insereSessaoSql.append(" WHERE id = ?;");
            ps = connection.prepareStatement(insereSessaoSql.toString());
            if (validacao == null) {
                ps.setString(1, formataData.format(custo.getData()));
                ps.setString(2, custo.getDescricao().trim());
                ps.setDouble(3, custo.getValor());
                ps.setString(4, custo.getNome().trim());                
                ps.executeUpdate();
                ps.setLong(5, custo.getId());
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
            String query = "select NEXTVAL('custo_seq')";
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

    public String validaCusto(Custo custo) {
        StringBuilder retorno = new StringBuilder();

        if (custo.getNome() == null || custo.getNome().trim().equals("")) {
            retorno.append("Nome deve ser preenchido.\n");
        }
        
        if (custo.getDescricao() == null || custo.getDescricao().trim().equals("")) {
            retorno.append("Descrição deve ser preenchido.\n");
        }
        
        if (custo.getValor() == null) {
            retorno.append("Valor inválido.\n");
        }
        
        if (custo.getData() == null) {
            retorno.append("Data inválido.\n");
        }
        
        if (retorno.toString().equals("")) {
            return null;
        }
        return retorno.toString();
    }
}
