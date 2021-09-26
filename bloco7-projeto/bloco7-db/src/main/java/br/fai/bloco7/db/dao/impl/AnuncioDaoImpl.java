package br.fai.bloco7.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.bloco7.db.connection.ConnectionFactory;
import br.fai.bloco7.db.dao.AnuncioDao;
import br.fai.bloco7.model.Anuncio;

@Repository
public class AnuncioDaoImpl implements AnuncioDao {

	@Override
	public List<Anuncio> readAll() {

		final List<Anuncio> anuncios = new ArrayList<Anuncio>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM anuncio";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Anuncio anuncio = new Anuncio();
				anuncio.setId(resultSet.getLong("id"));
				anuncio.setTipo(resultSet.getString("tipo"));
				anuncio.setPreco(resultSet.getFloat("preco"));
				anuncio.setLogradouroAnuncio(resultSet.getString("logradouro"));
				anuncio.setBairro(resultSet.getString("bairro"));

				anuncios.add(anuncio);

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return anuncios;
	}

	@Override
	public Anuncio readById(final Long id) {

		Anuncio anuncio = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM pessoa where id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				anuncio = new Anuncio();
				anuncio.setId(resultSet.getLong("id"));
				anuncio.setDescricao(resultSet.getString("descricao"));
				anuncio.setTipo(resultSet.getString("tipo"));
				anuncio.setPreco(resultSet.getFloat("preco"));
				anuncio.setLogradouroAnuncio(resultSet.getString("logradouro"));
				anuncio.setBairro(resultSet.getString("bairro"));

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return anuncio;
	}

	@Override
	public Long create(final Anuncio entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO anuncio";
		sql += " (descricao, tipo, preco,";
		sql += " logradouro, numero, bairro, cep, cidadeId, usuario_anunciante_id)";
		sql += "VALUES(?,?,?,?,?,?,?,?,?)";

		Long id = Long.valueOf(-1);

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getDescricao());
			preparedStatement.setString(2, entity.getTipo());
			preparedStatement.setFloat(3, entity.getPreco());
			preparedStatement.setString(4, entity.getLogradouroAnuncio());
			preparedStatement.setString(5, entity.getNumero());
			preparedStatement.setString(6, entity.getBairro());
			preparedStatement.setString(7, entity.getCep());
			preparedStatement.setLong(8, entity.getCidadeId());
			preparedStatement.setLong(9, entity.getUsuarioAnuncianteId());

			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();

		} catch (final Exception e) {

			try {
				connection.rollback();
			} catch (final SQLException e1) {
				System.out.println(e1.getMessage());
			}
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}

		return id;
	}

	@Override
	public boolean update(final Anuncio entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE anuncio SET";
		sql += " descricao = ?,";
		sql += " tipo = ?,";
		sql += " preco = ?,";
		sql += " logradouro = ?,";
		sql += " numero = ?,";
		sql += " bairro = ?,";
		sql += " cep = ?,";
		sql += " cidade_id = ?,";
		sql += " where id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getDescricao());
			preparedStatement.setString(2, entity.getTipo());
			preparedStatement.setFloat(3, entity.getPreco());
			preparedStatement.setString(4, entity.getLogradouroAnuncio());
			preparedStatement.setString(5, entity.getNumero());
			preparedStatement.setString(6, entity.getBairro());
			preparedStatement.setString(7, entity.getCep());
			preparedStatement.setLong(8, entity.getCidadeId());

			preparedStatement.execute();

			connection.commit();
			return true;

		} catch (final Exception e) {

			try {
				connection.rollback();
			} catch (final SQLException e1) {
				System.out.println(e1.getMessage());

			}
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public boolean delete(final Long id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String sql = "DELETE FROM anuncio WHERE id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

			preparedStatement.execute();

			connection.commit();

			return true;

		} catch (final Exception e) {

			try {
				connection.rollback();
			} catch (final SQLException e1) {
				System.out.println(e1.getMessage());

			}
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

}
