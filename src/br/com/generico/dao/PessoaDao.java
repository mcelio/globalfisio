/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.dao;

import br.com.generico.to.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class PessoaDao {

    public List<Pessoa> pesquisa(Pessoa pessoa) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Pessoa> lista = null;
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM pessoa WHERE");

            boolean and = false;
            if (pessoa.getCpf() != null && !pessoa.getCpf().trim().equals("")) {
                sql.append(" cpf = '" + pessoa.getCpf().trim().replace(".", "").replace("-", "") + "'");
                and = true;
            }

            if (pessoa.getNome() != null && !pessoa.getNome().trim().equals("")) {
                if (and) {
                    sql.append(" AND");
                }
                sql.append(" lower(nome) like '%" + pessoa.getNome().trim().toLowerCase() + "%'");
            }

            ps = connection.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Pessoa>();
            while (rs.next()) {
                Pessoa o = new Pessoa();
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
                o.setNumeroSus(rs.getString("numeroSus"));
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

    public void insere(Pessoa pessoa) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO pessoa(id, tipo, nome, \"nomeMae\", \"nomePai\", \"dataNascimento\", idade,");
            sql.append("cpf, rg, email, cns, telefone, celular, endereco, numero, bairro, estado, cidade, cep, \"dataCadastro\", \"numeroSus\")");
            sql.append("VALUES (NEXTVAL('pessoa_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?);");
            ps = connection.prepareStatement(sql.toString());
            String validacao = validaPessoa(pessoa);
            if (validacao == null) {
                ps.setInt(1, ("FISIOTERAPEUTA".equals(pessoa.getTipo()) ? Pessoa.FISIOTERAPEUTA : (("CLIENTE".equals(pessoa.getTipo())) ? Pessoa.CLIENTE : Pessoa.FORNECEDOR)));
                ps.setString(2, pessoa.getNome().trim());
                ps.setString(3, pessoa.getNomeMae().trim());
                ps.setString(4, pessoa.getNomePai().trim());
                Timestamp dataNascimento = new Timestamp(pessoa.getDataNascimento().getTime());
                ps.setTimestamp(5, dataNascimento);
                ps.setInt(6, Integer.parseInt(pessoa.getIdade()));
                ps.setString(7, pessoa.getCpf().trim());
                ps.setString(8, pessoa.getRg().trim());
                ps.setString(9, pessoa.getEmail().trim());
                ps.setString(10, pessoa.getCns().trim());
                ps.setString(11, pessoa.getTelefone().trim());
                ps.setString(12, pessoa.getCelular().trim());
                ps.setString(13, pessoa.getEndereco().trim());
                ps.setInt(14, Integer.parseInt(pessoa.getNumero().trim()));
                ps.setString(15, pessoa.getBairro().trim());
                ps.setString(16, pessoa.getEstado().trim());
                ps.setString(17, pessoa.getCidade().trim());
                ps.setString(18, pessoa.getCep().trim());
                ps.setString(19, pessoa.getNumeroSus().trim());
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

    public void atualiza(Pessoa pessoa) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = Dao.getInstance().getConnection();
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE pessoa SET tipo = ?, nome = ?, \"nomeMae\" = ?, \"nomePai\" = ?, \"dataNascimento\" = ?, idade = ?, ");
            sql.append("cpf = ?, rg = ?, email = ?, cns = ?, telefone = ?, celular = ?, endereco = ? , numero = ?, bairro = ?, estado = ?, cidade = ?, cep = ?, \"numeroSus\" = ?");
            sql.append("WHERE id = ?");
            ps = connection.prepareStatement(sql.toString());
            String validacao = validaPessoa(pessoa);
            if (validacao == null) {
                ps.setInt(1, ("FISIOTERAPEUTA".equals(pessoa.getTipo()) ? Pessoa.FISIOTERAPEUTA : (("CLIENTE".equals(pessoa.getTipo())) ? Pessoa.CLIENTE : Pessoa.FORNECEDOR)));
                ps.setString(2, pessoa.getNome().trim());
                ps.setString(3, pessoa.getNomeMae().trim());
                ps.setString(4, pessoa.getNomePai().trim());
                Timestamp dataNascimento = new Timestamp(pessoa.getDataNascimento().getTime());
                ps.setTimestamp(5, dataNascimento);
                ps.setInt(6, Integer.parseInt(pessoa.getIdade().trim()));
                ps.setString(7, pessoa.getCpf().trim());
                ps.setString(8, pessoa.getRg().trim());
                ps.setString(9, pessoa.getEmail().trim());
                ps.setString(10, pessoa.getCns().trim());
                ps.setString(11, pessoa.getTelefone().trim());
                ps.setString(12, pessoa.getCelular().trim());
                ps.setString(13, pessoa.getEndereco().trim());
                ps.setInt(14, Integer.parseInt(pessoa.getNumero().trim()));
                ps.setString(15, pessoa.getBairro().trim());
                ps.setString(16, pessoa.getEstado().trim());
                ps.setString(17, pessoa.getCidade().trim());
                ps.setString(18, pessoa.getCep().trim());
                ps.setString(19, pessoa.getNumeroSus().trim());
                ps.setLong(20, pessoa.getId());

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

    public String validaPessoa(Pessoa pessoa) {
        StringBuilder retorno = new StringBuilder();
        if (pessoa.getNome() == null || pessoa.getNome().trim().equals("")) {
            retorno.append("Nome deve ser preenchido.\n");
        }

        if (pessoa.getNome() != null && pessoa.getNome().trim().length() > 50) {
            retorno.append("O tamanho limite para o campo Nome é 50 caracteres.\n");
        }

        if (pessoa.getNomeMae() == null || pessoa.getNomeMae().trim().equals("")) {
            retorno.append("Nome da mãe deve ser preenchido.\n");
        }

        if (pessoa.getNomeMae() != null && pessoa.getNomeMae().trim().length() > 50) {
            retorno.append("O tamanho limite para o campo Nome da Mãe é 50 caracteres.\n");
        }

        if (pessoa.getNomePai() == null || pessoa.getNomePai().trim().equals("")) {
            retorno.append("Nome do pai deve ser preenchido.\n");
        }

        if (pessoa.getNomePai() != null && pessoa.getNomePai().trim().length() > 50) {
            retorno.append("O tamanho limite para o campo Nome do Pai é 50 caracteres.\n");
        }

        if (pessoa.getDataNascimento() == null) {
            retorno.append("Data de nascimento deve ser preenchido no formato dd/MM/AAAA.\n");
        }

        if (pessoa.getIdade() == null || pessoa.getIdade().trim().equals("")) {
            retorno.append("Idade deve ser preenchido.\n");
        } else if (pessoa.getIdade() != null && pessoa.getIdade().trim().length() > 3) {
            retorno.append("O tamanho limite para o campo Idade é 3 caracteres.\n");
        } else {
            try {
                Integer.parseInt(pessoa.getIdade());
            } catch (Exception e) {
                retorno.append("Idade deve ser um número.\n");
            }
        }

        if (pessoa.getCpf() == null || pessoa.getCpf().trim().equals("")) {
            retorno.append("CPF deve ser preenchido.\n");
        }

        if (pessoa.getCpf() != null && pessoa.getCpf().trim().replace(".", "").replace("-", "").length() > 12) {
            retorno.append("O tamanho limite para o campo CPF é 12 caracteres.\n");
        }

        if (pessoa.getRg() == null || pessoa.getRg().trim().equals("")) {
            retorno.append("RG deve ser preenchido.\n");
        }

        if (pessoa.getRg() != null && pessoa.getRg().trim().replace(".", "").replace("-", "").length() > 10) {
            retorno.append("O tamanho limite para o campo RG é 10 caracteres.\n");
        }

        if (pessoa.getEmail() == null || pessoa.getEmail().trim().equals("")) {
            retorno.append("E-mail deve ser preenchido.\n");
        }

        if (pessoa.getEmail() != null && pessoa.getEmail().trim().length() > 50) {
            retorno.append("O tamanho limite para o campo E-mail é 50 caracteres.\n");
        }

        if (pessoa.getCns() == null || pessoa.getCns().trim().equals("")) {
            retorno.append("CNS deve ser preenchido.\n");
        }

        if (pessoa.getCns() != null && pessoa.getCns().trim().length() > 30) {
            retorno.append("O tamanho limite para o campo CNS é 30 caracteres.\n");
        }

        if (pessoa.getTelefone() == null || pessoa.getTelefone().trim().equals("")) {
            retorno.append("Telefone deve ser preenchido.\n");
        }

        if (pessoa.getTelefone() != null && pessoa.getTelefone().trim().length() > 12) {
            retorno.append("O tamanho limite para o campo Telefone é 12 caracteres.\n");
        }

        if (pessoa.getCelular() == null || pessoa.getCelular().trim().equals("")) {
            retorno.append("Celular deve ser preenchido.\n");
        }

        if (pessoa.getCelular() != null && pessoa.getCelular().trim().length() > 12) {
            retorno.append("O tamanho limite para o campo Celular é 12 caracteres.\n");
        }

        if (pessoa.getEndereco() == null || pessoa.getEndereco().trim().equals("")) {
            retorno.append("Endereco deve ser preenchido.\n");
        }

        if (pessoa.getEndereco() != null && pessoa.getEndereco().trim().length() > 50) {
            retorno.append("O tamanho limite para o campo Endereco é 50 caracteres.\n");
        }

        if (pessoa.getNumero() == null || pessoa.getNumero().trim().equals("")) {
            retorno.append("Número deve ser preenchido.\n");
        } else {
            try {
                Integer.parseInt(pessoa.getNumero());
            } catch (Exception e) {
                retorno.append("Número deve ser um número.\n");
            }
        }

        if (pessoa.getBairro() == null || pessoa.getBairro().trim().equals("")) {
            retorno.append("Bairro deve ser preenchido.\n");
        }

        if (pessoa.getBairro() != null && pessoa.getBairro().trim().length() > 30) {
            retorno.append("O tamanho limite para o campo Bairro é 30 caracteres.\n");
        }

        if (pessoa.getCidade() == null || pessoa.getCidade().trim().equals("")) {
            retorno.append("Cidade deve ser preenchido.\n");
        }

        if (pessoa.getCidade() != null && pessoa.getCidade().trim().length() > 40) {
            retorno.append("O tamanho limite para o campo Cidade é 40 caracteres.\n");
        }

        if (pessoa.getCep() == null || pessoa.getCep().trim().equals("")) {
            retorno.append("CEP deve ser preenchido.\n");
        }

        if (pessoa.getCep() != null && pessoa.getCep().trim().length() > 9) {
            retorno.append("O tamanho limite para o campo CEP é 9 caracteres.\n");
        }

        if (pessoa.getNumeroSus() == null || pessoa.getNumeroSus().trim().equals("")) {
            retorno.append("Número do SUS deve ser preenchido.\n");
        }
        
        if (pessoa.getNumeroSus() != null && pessoa.getNumeroSus().trim().length() > 16) {
            retorno.append("O tamanho limite para o campo Número do SUS é 16 caracteres.\n");
        }

        if (retorno.length() > 0) {
            return retorno.toString();
        }
        return null;
    }
}
