/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.dao;

import br.com.generico.to.Guia;
import br.com.generico.to.Pessoa;
import br.com.generico.to.Sessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class SessaoDao {

    private DateFormat formataDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public SessaoDao() {
    }

    public List<Sessao> pesquisa(Sessao sessao, String dataDe, String dataAte) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;        
        List<Sessao> lista = new ArrayList<Sessao>();
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM sessao s");
            sql.append(" INNER JOIN pessoa p ON (p.id = s.cliente_id)");
            sql.append(" WHERE");

            boolean and = false;
            if (sessao.getPessoa() != null) {
                if (sessao.getPessoa().getCpf() != null && !sessao.getPessoa().getCpf().trim().equals("")) {
                    sql.append(" p.cpf = '" + sessao.getPessoa().getCpf().replace(".", "").replace("-", "") + "'");
                    and = true;
                }

                if (sessao.getPessoa().getCns() != null && !sessao.getPessoa().getCns().trim().equals("")) {
                    if (and) {
                        sql.append(" and");
                    }
                    sql.append(" p.cns = '" + sessao.getPessoa().getCns().replace(".", "").replace("-", "") + "'");
                    and = true;
                }

                if (sessao.getPessoa().getNome() != null && !sessao.getPessoa().getNome().trim().equals("")) {
                    if (and) {
                        sql.append(" and");
                    }
                    sql.append(" lower(p.nome) like '%" + sessao.getPessoa().getNome().trim().toLowerCase() + "%'");
                    and = true;
                }
            }

            if (dataDe != null && dataAte != null) {
                if (and) {
                    sql.append(" and");
                }
                sql.append(" s.data between '" + dataDe + "' and '" + dataAte + "'");
            }
            
            sql.append(" order by s.data");
            
            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sessao o = new Sessao();
                o.setConvenio(rs.getString("convenio"));
                o.setData(rs.getTimestamp("data"));
                o.setDiagnostico(rs.getString("diagnostico"));
                o.setExame(rs.getString("exame"));
                Long idSessao = rs.getLong("id");
                o.setId(idSessao);
                Long idPessoa = rs.getLong("cliente_id");
                Pessoa pessoa = getPessoaById(connection, idPessoa);
                o.setPessoa(pessoa);
                o.setGuias(guias(idSessao, connection));
                lista.add(o);
            }
            return lista;

        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
            connection.close();
        }

    }

    private Pessoa getPessoaById(Connection connection, Long id) throws Exception {
        PreparedStatement ps = null;
        Pessoa o = null;
        try {            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM pessoa WHERE id = " + id);
            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                o = new Pessoa();
                o.setId(rs.getLong("id"));
                int tipo = rs.getInt("tipo");
                o.setTipo((tipo == Pessoa.FISIOTERAPEUTA ? "FISIOTERAPEUTA"
                        : (tipo == Pessoa.CLIENTE ? "CLIENTE" : "FORNECEDOR")));
                o.setNome(rs.getString("nome"));
                o.setNomeMae(rs.getString("nomeMae"));
                o.setNomePai(rs.getString("nomePai"));
                o.setDataNascimento(rs.getDate("dataNascimento"));
                o.setIdade(String.valueOf(rs.getInt("idade")));
                o.setCpf(rs.getString("cpf"));
                o.setRg(rs.getString("rg"));
                o.setCpf(rs.getString("cpf"));
                o.setEmail(rs.getString("email"));
                o.setCns(rs.getString("cns"));
                o.setTelefone(rs.getString("telefone"));
                o.setCelular(rs.getString("celular"));
                o.setEndereco(rs.getString("endereco"));
                o.setNumero(rs.getString("numero"));
                o.setBairro(rs.getString("bairro"));
                o.setCidade(rs.getString("cidade"));
                o.setEstado(rs.getString("estado"));
                o.setCep(rs.getString("cep"));
                o.setDataCadastro(rs.getDate("dataCadastro"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return o;
    }

    /**
     * Recupera guias por sessão (Passagem de connection)
     * 
     * 
     * @param idSessao
     * @param connection
     * @return
     * @throws Exception 
     */
    private List<Guia> guias(Long idSessao, Connection connection) throws Exception {
        PreparedStatement ps = null;
        List<Guia> lista = new ArrayList<Guia>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM guia WHERE id = " + idSessao);
            sql.append(" ORDER BY to_timestamp(data || ' ' || hora, 'DD/MM/YYYY HH24:MI') DESC");
            sql.append(" LIMIT 10");
            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            Sessao sessao = new Sessao();
            sessao.setId(idSessao);            
            while (rs.next()) {
                Guia o = new Guia();
                o.setSessao(sessao);
                o.setIndice(rs.getInt("indice"));
                o.setData(rs.getString("data"));
                o.setDescricao((rs.getString("descricao") == null ? null : rs.getString("descricao").trim()));
                o.setHora(rs.getString("hora"));
                o.setFeito((rs.getInt("feito") == 0 ? false : true));
                lista.add(o);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            ps.close();
        }
        return lista;
    }
    
    
    /**
     * Recupera guias por sessão (Nova connection)
     * 
     * 
     * @param idSessao
     * @param connection
     * @return
     * @throws Exception 
     */
    public List<Guia> guias(Long idSessao, boolean limit) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Guia> lista = new ArrayList<Guia>();
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM guia WHERE id = " + idSessao);
            sql.append(" ORDER BY to_timestamp(data || ' ' || hora, 'DD/MM/YYYY HH24:MI') DESC");            
            if(limit){
                sql.append(" LIMIT 10");
            }
            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            Sessao sessao = new Sessao();
            sessao.setId(idSessao);            
            while (rs.next()) {
                Guia o = new Guia();
                o.setSessao(sessao);
                o.setIndice(rs.getInt("indice"));
                o.setData(rs.getString("data"));
                o.setDescricao((rs.getString("descricao") == null ? null : rs.getString("descricao").trim()));
                o.setHora(rs.getString("hora"));
                o.setFeito((rs.getInt("feito") == 0 ? false : true));
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

    public void inserir(Sessao sessao) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String validacao = validaSessao(sessao);

            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder insereSessaoSql = new StringBuilder();
            insereSessaoSql.append("INSERT INTO sessao(");
            insereSessaoSql.append("id, data, cliente_id, convenio, diagnostico, exame)");
            insereSessaoSql.append("VALUES (?, ?, ?, ?, ?, ?);");
            ps = connection.prepareStatement(insereSessaoSql.toString());
            if (validacao == null) {
                Long nextId = getSequenceNextVal();
                ps.setLong(1, nextId);
                //Timestamp data = new Timestamp(sessao.getData().getTime());
                Timestamp data = new Timestamp(new Date().getTime());
                //ps.setString(2, formataDataHora.format(sessao.getData()));
                ps.setTimestamp(2, data);
                ps.setLong(3, sessao.getPessoa().getId());
                ps.setString(4, sessao.getConvenio().trim());
                ps.setString(5, sessao.getDiagnostico().trim());
                ps.setString(6, sessao.getExame().trim());
                ps.executeUpdate();
                if (sessao.getGuias() != null) {
                    for (Guia guia : sessao.getGuias()) {
                        StringBuilder insereGuiaSql = new StringBuilder();
                        insereGuiaSql.append("INSERT INTO guia(");
                        insereGuiaSql.append("id, indice, descricao, data, hora, feito)");
                        insereGuiaSql.append("VALUES (?, ?, ?, ?, ?, ?);");
                        ps = connection.prepareStatement(insereGuiaSql.toString());
                        ps.setLong(1, nextId);
                        ps.setLong(2, guia.getIndice());
                        ps.setString(3, (guia.getDescricao() == null) ? null : guia.getDescricao().trim());
                        ps.setString(4, (guia.getData() == null) ? null : guia.getData().trim());
                        ps.setString(5, (guia.getHora() == null) ? null : guia.getHora().trim());
                        ps.setInt(6, (guia.getFeito() ? 1 : 0));
                        ps.executeUpdate();
                    }
                }
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

    public void atualiza(Sessao sessao) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String validacao = validaSessao(sessao);
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder insereSessaoSql = new StringBuilder();
            insereSessaoSql.append("UPDATE sessao SET");
            insereSessaoSql.append(" data = ?, cliente_id  = ?, convenio  = ?, diagnostico = ?, exame = ?");
            insereSessaoSql.append(" WHERE id = ?");
            ps = connection.prepareStatement(insereSessaoSql.toString());
            if (validacao == null) {
                //Timestamp data = new Timestamp(sessao.getData().getTime());
                Timestamp data = new Timestamp(new Date().getTime());
                ps.setTimestamp(1, data);
                ps.setLong(2, sessao.getPessoa().getId());
                ps.setString(3, sessao.getConvenio().trim());
                ps.setString(4, sessao.getDiagnostico().trim());
                ps.setString(5, sessao.getExame().trim());
                ps.setLong(6, sessao.getId());
                ps.executeUpdate();

                // atualiza guias
                StringBuilder atualizaGuiaSql = new StringBuilder();
                atualizaGuiaSql.append("DELETE FROM guia WHERE id = " + sessao.getId());
                ps = connection.prepareStatement(atualizaGuiaSql.toString());
                ps.executeUpdate();
                for (Guia guia : sessao.getGuias()) {
                    StringBuilder insereGuiaSql = new StringBuilder();
                    insereGuiaSql.append("INSERT INTO guia(");
                    insereGuiaSql.append("id, indice, descricao, data, hora, feito)");
                    insereGuiaSql.append("VALUES (?, ?, ?, ?, ?, ?);");
                    ps = connection.prepareStatement(insereGuiaSql.toString());
                    ps.setLong(1, sessao.getId());
                    ps.setLong(2, guia.getIndice());
                    ps.setString(3, (guia.getDescricao() == null) ? null : guia.getDescricao().trim());
                    ps.setString(4, (guia.getData() == null) ? null : guia.getData().trim());
                    ps.setString(5, (guia.getHora() == null) ? null : guia.getHora().trim());
                    ps.setInt(6, (guia.getFeito() ? 1 : 0));
                    ps.executeUpdate();
                }
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
            String query = "select NEXTVAL('sessao_seq')";
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            Statement stm = connection.createStatement();
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

    public String validaSessao(Sessao sessao) {
        StringBuilder retorno = new StringBuilder();

        /*if (sessao.getData() == null) {
            retorno.append("Data deve ser preenchida.\n");
        }*/

        if (sessao.getPessoa() == null || sessao.getPessoa().getId() == null) {
            retorno.append("Cliente deve ser preenchido.\n");
        }

        if (sessao.getDiagnostico() == null || sessao.getDiagnostico().trim().equals("")) {
            retorno.append("Diagnóstico deve ser preenchido.\n");
        }

        if (sessao.getDiagnostico() != null && sessao.getDiagnostico().trim().length() > 60) {
            retorno.append("O tamanho limite para o campo Diagnóstico é 60 caracteres.\n");
        }

        if (sessao.getExame() == null || sessao.getExame().trim().equals("")) {
            retorno.append("Exame deve ser preenchido.\n");
        }
        if (sessao.getExame() != null && sessao.getExame().trim().length() > 30) {
            retorno.append("O tamanho limite para o campo Exame é 30 caracteres.\n");
        }
        if (sessao.getGuias() != null) {
            for (Guia guia : sessao.getGuias()) {
                if (guia.getDescricao() != null && guia.getDescricao().trim().length() > 60) {
                    retorno.append("O tamanho limite para o campo Descrição da Sessão é 60 caracteres.\n");
                }
            }
        }
        if (retorno.toString().equals("")) {
            return null;
        }
        return retorno.toString();
    }
}
