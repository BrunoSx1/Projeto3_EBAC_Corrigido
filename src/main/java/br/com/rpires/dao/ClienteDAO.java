/**
 * 
 */
package br.com.rpires.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Cliente;

/**
 * DAO para a entidade Cliente.
 * 
 * @author rodrigo.pires
 *
 */
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

    public ClienteDAO() {
        super();
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualiarDados(Cliente entity, Cliente entityCadastrado) {
        entityCadastrado.setCidade(entity.getCidade());
        entityCadastrado.setCpf(entity.getCpf());
        entityCadastrado.setEnd(entity.getEnd());
        entityCadastrado.setEstado(entity.getEstado());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setNumero(entity.getNumero());
        entityCadastrado.setTel(entity.getTel());
        // Adicione o novo campo email
        entityCadastrado.setEmail(entity.getEmail());
    }

    @Override
    protected String getQueryInsercao() {
        return "INSERT INTO TB_CLIENTE (ID, NOME, CPF, TEL, ENDERECO, NUMERO, CIDADE, ESTADO, EMAIL) VALUES (nextval('sq_cliente'),?,?,?,?,?,?,?,?)";
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Cliente entity) throws SQLException {
        stmInsert.setString(1, entity.getNome());
        stmInsert.setLong(2, entity.getCpf());
        stmInsert.setLong(3, entity.getTel());
        stmInsert.setString(4, entity.getEnd());
        stmInsert.setLong(5, entity.getNumero());
        stmInsert.setString(6, entity.getCidade());
        stmInsert.setString(7, entity.getEstado());
        // Adicione o novo campo email
        stmInsert.setString(8, entity.getEmail());
    }

    @Override
    protected String getQueryExclusao() {
        return "DELETE FROM TB_CLIENTE WHERE CPF = ?";
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) throws SQLException {
        stmExclusao.setLong(1, valor);
    }

    @Override
    protected String getQueryAtualizacao() {
        return "UPDATE TB_CLIENTE SET NOME = ?, TEL = ?, ENDERECO = ?, NUMERO = ?, CIDADE = ?, ESTADO = ?, EMAIL = ? WHERE CPF = ?";
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Cliente entity) throws SQLException {
        stmUpdate.setString(1, entity.getNome());
        stmUpdate.setLong(2, entity.getTel());
        stmUpdate.setString(3, entity.getEnd());
        stmUpdate.setLong(4, entity.getNumero());
        stmUpdate.setString(5, entity.getCidade());
        stmUpdate.setString(6, entity.getEstado());
        // Adicione o novo campo email
        stmUpdate.setString(7, entity.getEmail());
        stmUpdate.setLong(8, entity.getCpf());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
        stmSelect.setLong(1, valor);
    }

    // Adicione este método para recuperar o cliente por email
    public Cliente obterPorEmail(String email) {
        String query = "SELECT * FROM TB_CLIENTE WHERE EMAIL = ?";
        try (PreparedStatement stmSelect = getConexao().prepareStatement(query)) {
            stmSelect.setString(1, email);
            try (ResultSet resultSet = stmSelect.executeQuery()) {
                if (resultSet.next()) {
                    return popularEntidade(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
