package PacoteJavaGUI;

import java.sql.*;
import java.util.*;

public class ProdutoDAO {
    private Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String inserir(ProdutoBean produto) {
        String sql = "insert into produto(Codigo,Nome,Preco,Marca)values (?,?,?,?)";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getPreco());
            ps.setString(4, produto.getMarca());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String alterar(ProdutoBean produto) {
        String sql = "update produto set Nome = ?, Preco = ?, Marca = ? where Codigo = ?";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getPreco());
            ps.setString(3, produto.getMarca());
            ps.setString(4, produto.getCodigo());

            if (ps.executeUpdate() > 0) {
                return "alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String excluir(ProdutoBean produto) {
        String sql = "delete from produto where Codigo = ?";
 
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, produto.getCodigo());

            if (ps.executeUpdate() > 0) {
                return "excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<ProdutoBean> listar() {
        String sql = "select * from Produto";

        List<ProdutoBean> listarProduto = new ArrayList<ProdutoBean>();

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ProdutoBean pb = new ProdutoBean();
                    pb.setCodigo(rs.getString(1));
                    pb.setNome(rs.getString(2));
                    pb.setPreco(rs.getString(3));
                    pb.setMarca(rs.getString(4));
                    listarProduto.add(pb);
                }
                return listarProduto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
}