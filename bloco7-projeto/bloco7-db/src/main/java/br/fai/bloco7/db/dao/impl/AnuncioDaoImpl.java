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

			final String sql = "select A.*, C.nome from anuncio A  inner join cidade C on C.id = A.cidade_id";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Anuncio anuncio = new Anuncio();
				anuncio.setId(resultSet.getLong("id"));
				anuncio.setDescricao(resultSet.getString("descricao"));
				anuncio.setQuartos(resultSet.getInt("quartos"));
				anuncio.setBanheiros(resultSet.getInt("banheiros"));
				anuncio.setVaga_garagem(resultSet.getInt("vaga_garagem"));
				anuncio.setTipo_propriedade(resultSet.getString("tipo_propriedade"));
				anuncio.setStatus(resultSet.getString("status"));
				anuncio.setArea(resultSet.getInt("area"));
				anuncio.setPreco(resultSet.getFloat("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));

				anuncios.add(anuncio);

			}

		} catch (final Exception e) {

			System.out.println(e.getMessage());

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

			final String sql = "SELECT * FROM anuncio where id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				anuncio = new Anuncio();
				anuncio.setId(resultSet.getLong("id"));
				anuncio.setDescricao(resultSet.getString("descricao"));
				anuncio.setQuartos(resultSet.getInt("quartos"));
				anuncio.setBanheiros(resultSet.getInt("banheiros"));
				anuncio.setVaga_garagem(resultSet.getInt("vaga_garagem"));
				anuncio.setTipo_propriedade(resultSet.getString("tipo_propriedade"));
				anuncio.setStatus(resultSet.getString("status"));
				anuncio.setArea(resultSet.getInt("area"));
				anuncio.setPreco(resultSet.getFloat("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));

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
		sql += " (descricao, quartos, banheiros,";
		sql += " vaga_garagem, tipo_propriedade, status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id)";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Long id = Long.valueOf(-1);

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getDescricao());
			preparedStatement.setInt(2, entity.getQuartos());
			preparedStatement.setInt(3, entity.getBanheiros());
			preparedStatement.setInt(4, entity.getVaga_garagem());
			preparedStatement.setString(5, entity.getTipo_propriedade());
			preparedStatement.setString(6, entity.getStatus());
			preparedStatement.setInt(7, entity.getArea());
			preparedStatement.setFloat(8, entity.getPreco());
			preparedStatement.setString(9, entity.getEndereco());
			preparedStatement.setString(10, entity.getBairro());
			preparedStatement.setString(11, entity.getCep());
			preparedStatement.setLong(12, 1);
			preparedStatement.setLong(13, 1);

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

		String sql = "UPDATE anuncio SET descricao = ?,";
		sql += " quartos = ?,";
		sql += " banheiros = ?,";
		sql += " vaga_garagem = ?,";
		sql += " tipo_propriedade = ?,";
		sql += " status = ?,";
		sql += " area = ?,";
		sql += " preco = ?";
//		sql += " endereco = ?,";
//		sql += " bairro = ?,";
//		sql += " cep = ?,";
//		sql += " cidade_id = ?,";
		sql += " where id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getDescricao());
			preparedStatement.setInt(2, entity.getQuartos());
			preparedStatement.setInt(3, entity.getBanheiros());
			preparedStatement.setInt(4, entity.getVaga_garagem());
			preparedStatement.setString(5, entity.getTipo_propriedade());
			preparedStatement.setString(6, entity.getStatus());
			preparedStatement.setInt(7, entity.getArea());
			preparedStatement.setFloat(8, entity.getPreco());
//			preparedStatement.setString(9, entity.getEndereco());
//			preparedStatement.setString(10, entity.getBairro());
//			preparedStatement.setString(11, entity.getCep());
//			preparedStatement.setLong(12, entity.getCidadeId());

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

	@Override
	public List<Anuncio> pesquisar(final Anuncio entity) {

		final List<Anuncio> anuncios = new ArrayList<Anuncio>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "select A.*, C.nome from anuncio A  inner join cidade C on C.id = A.cidade_id";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Anuncio anuncio = new Anuncio();
				anuncio.setId(resultSet.getLong("id"));
				anuncio.setDescricao(resultSet.getString("descricao"));
				anuncio.setQuartos(resultSet.getInt("quartos"));
				anuncio.setBanheiros(resultSet.getInt("banheiros"));
				anuncio.setVaga_garagem(resultSet.getInt("vaga_garagem"));
				anuncio.setTipo_propriedade(resultSet.getString("tipo_propriedade"));
				anuncio.setStatus(resultSet.getString("status"));
				anuncio.setArea(resultSet.getInt("area"));
				anuncio.setPreco(resultSet.getFloat("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));

				anuncios.add(anuncio);

			}

		} catch (final Exception e) {

			System.out.println(e.getMessage());

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return anuncios;
	}

}
