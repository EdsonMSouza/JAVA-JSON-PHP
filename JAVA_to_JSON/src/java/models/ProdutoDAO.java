package models;

import beans.Produto;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson Melo de Souza
 */
public class ProdutoDAO {

    private Connection conexao = null;

    public ProdutoDAO() throws SQLException {
        // retorna a conexao no momento da chamada da classe
        this.conexao = ConnectionFactory.getInstance().getConnection();
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM produto ORDER BY id ASC";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            // Realiza a consulta e retorna um ResultSet
            ResultSet rs = ps.executeQuery();

            // Lista para armazenar os dados do produto
            List<Produto> produtos = new ArrayList<>();

            /**
             * Percorre os registros, cria os objetos e coloca na lista de
             * produtos
             */
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                produtos.add(p);
            }
            return produtos;

        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao listar os produtos", ex);
        }
    }
}
