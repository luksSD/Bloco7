package br.fai.bloco7.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.fai.bloco7.db.connection.ConnectionFactory;
import br.fai.bloco7.db.dao.AnuncioDao;
import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.model.Recentes;

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
				anuncio.setPreco(resultSet.getString("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));
				anuncio.setFoto1(resultSet.getString("foto1"));
				anuncio.setFoto2(resultSet.getString("foto2"));
				anuncio.setFoto3(resultSet.getString("foto3"));
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

			String sql = "SELECT * FROM anuncio a";
			sql += " inner join cidade c on a.cidade_id = c.id";
			sql += " where a.id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				anuncio = new Anuncio();
				anuncio.setId(resultSet.getLong("id"));
				anuncio.setDescricao(resultSet.getString("descricao"));
				anuncio.setQuartos(resultSet.getInt("quartos"));
				anuncio.setBanheiros(resultSet.getInt("banheiros"));
				anuncio.setVaga_garagem(resultSet.getInt("vaga_garagem"));
				anuncio.setTipo_propriedade(resultSet.getString("tipo_propriedade"));
				anuncio.setStatus(resultSet.getString("status"));
				anuncio.setArea(resultSet.getInt("area"));
				anuncio.setPreco(resultSet.getString("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));
				anuncio.setVideo(resultSet.getString("video"));
				anuncio.setFoto1(resultSet.getString("foto1"));
				if (resultSet.getString("foto2") != null) {
					anuncio.setFoto2(resultSet.getString("foto2"));
				}
				if (resultSet.getString("foto3") != null) {
					anuncio.setFoto3(resultSet.getString("foto3"));
				}
				if (resultSet.getString("foto4") != null) {
					anuncio.setFoto4(resultSet.getString("foto4"));
				}
				if (resultSet.getString("foto5") != null) {
					anuncio.setFoto5(resultSet.getString("foto5"));
				}

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
		sql += " vaga_garagem, tipo_propriedade, status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id, foto1, foto2, foto3, foto4, foto5, video) ";
		sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			preparedStatement.setString(8, entity.getPreco());
			preparedStatement.setString(9, entity.getEndereco());
			preparedStatement.setString(10, entity.getBairro());
			preparedStatement.setString(11, entity.getCep());
			preparedStatement.setLong(12, entity.getCidadeId());
			preparedStatement.setLong(13, entity.getUsuarioAnuncianteId());
			preparedStatement.setString(14, "/resources/img/" + entity.getFoto1());

			if (entity.getFoto2() != "") {
				preparedStatement.setString(15, "/resources/img/" + entity.getFoto2());
			} else {
				preparedStatement.setString(15, null);
			}

			if (entity.getFoto3() != "") {
				preparedStatement.setString(16, "/resources/img/" + entity.getFoto3());
			} else {
				preparedStatement.setString(16, null);
			}

			if (entity.getFoto4() != "") {
				preparedStatement.setString(17, "/resources/img/" + entity.getFoto4());
			} else {
				preparedStatement.setString(17, null);
			}

			if (entity.getFoto5() != "") {
				preparedStatement.setString(18, "/resources/img/" + entity.getFoto5());
			} else {
				preparedStatement.setString(18, null);
			}
			preparedStatement.setString(19, entity.getVideo());

			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();

		} catch (final Exception e) {

			System.out.println(e.getMessage());

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
		sql += " preco = ?,";
		sql += " endereco = ?,";
		sql += " bairro = ?,";
		sql += " cep = ?,";
		sql += " cidade_id = ?";
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
			preparedStatement.setString(8, entity.getPreco());
			preparedStatement.setString(9, entity.getEndereco());
			preparedStatement.setString(10, entity.getBairro());
			preparedStatement.setString(11, entity.getCep());
			preparedStatement.setLong(12, entity.getCidadeId());
			preparedStatement.setLong(13, entity.getId());

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
	public List<Anuncio> readByCriteria(final Map<String, String> criteria) {

		final List<Anuncio> anuncios = new ArrayList<Anuncio>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "select A.*, C.nome from anuncio A  inner join cidade C on C.id = A.cidade_id WHERE TRUE";

			if (criteria != null && criteria.size() != 0) {

				for (final String chave : criteria.keySet()) {
					sql += " and " + chave + " " + criteria.get(chave);
				}
			}

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
				anuncio.setPreco(resultSet.getString("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));
				anuncio.setFoto1(resultSet.getString("foto1"));
				anuncio.setFoto2(resultSet.getString("foto2"));
				anuncio.setFoto3(resultSet.getString("foto3"));
				anuncio.setFoto4(resultSet.getString("foto4"));
				anuncio.setFoto5(resultSet.getString("foto5"));

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
	public List<Anuncio> readByAnuncianteId(final Long idAnunciante) {

		final List<Anuncio> anuncios = new ArrayList<Anuncio>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "select A.*, C.nome from anuncio A  inner join cidade C on C.id = A.cidade_id WHERE TRUE and usuario_anunciante_id = ";
			sql += idAnunciante;

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
				anuncio.setPreco(resultSet.getString("preco"));
				anuncio.setEndereco(resultSet.getString("endereco"));
				anuncio.setBairro(resultSet.getString("bairro"));
				anuncio.setCep(resultSet.getString("cep"));
				anuncio.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				anuncio.setCidadeId(resultSet.getLong("cidade_id"));
				anuncio.setNomeCidade(resultSet.getString("nome"));
				anuncio.setFoto1(resultSet.getString("foto1"));
				anuncio.setFoto2(resultSet.getString("foto2"));
				anuncio.setFoto3(resultSet.getString("foto3"));
				anuncio.setFoto4(resultSet.getString("foto4"));
				anuncio.setFoto5(resultSet.getString("foto5"));

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
	public List<Recentes> readRecents() {

		final List<Recentes> recentes = new ArrayList<Recentes>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "select A.*, C.nome from anuncio A  inner join cidade C on C.id = A.cidade_id order by A.id desc limit 4";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Recentes recente = new Recentes();
				recente.setId(resultSet.getLong("id"));
				recente.setDescricao(resultSet.getString("descricao"));
				recente.setQuartos(resultSet.getInt("quartos"));
				recente.setBanheiros(resultSet.getInt("banheiros"));
				recente.setVaga_garagem(resultSet.getInt("vaga_garagem"));
				recente.setTipo_propriedade(resultSet.getString("tipo_propriedade"));
				recente.setStatus(resultSet.getString("status"));
				recente.setArea(resultSet.getInt("area"));
				recente.setPreco(resultSet.getString("preco"));
				recente.setEndereco(resultSet.getString("endereco"));
				recente.setBairro(resultSet.getString("bairro"));
				recente.setCep(resultSet.getString("cep"));
				recente.setUsuarioAnuncianteId(resultSet.getLong("usuario_anunciante_id"));
				recente.setCidadeId(resultSet.getLong("cidade_id"));
				recente.setNomeCidade(resultSet.getString("nome"));
				recente.setFoto1(resultSet.getString("foto1"));
				recente.setFoto2(resultSet.getString("foto2"));
				recente.setFoto3(resultSet.getString("foto3"));
				recente.setFoto4(resultSet.getString("foto4"));
				recente.setFoto5(resultSet.getString("foto5"));
				recentes.add(recente);

			}

		} catch (final Exception e) {

			System.out.println(e.getMessage());

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return recentes;
	}

}
