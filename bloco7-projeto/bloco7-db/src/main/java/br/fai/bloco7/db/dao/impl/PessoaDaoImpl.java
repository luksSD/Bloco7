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
import br.fai.bloco7.db.dao.PessoaDao;
import br.fai.bloco7.model.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao {

	@Override
	public List<Pessoa> readAll() {

		final List<Pessoa> users = new ArrayList<Pessoa>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM pessoa";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Pessoa person = new Pessoa();
				person.setId(resultSet.getLong("id"));
				person.setCpf(resultSet.getString("cpf"));
				person.setNome(resultSet.getString("nome"));
				person.setLogradouro(resultSet.getString("logradouro"));
				person.setBairro(resultSet.getString("bairro"));
				person.setCidadeId(resultSet.getLong("cidade_id"));
				person.setCep(resultSet.getString("cep"));
				person.setNumero(resultSet.getString("numero"));

				users.add(person);

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return users;

	}

	@Override
	public Pessoa readById(final Long id) {

		Pessoa person = null;

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

				person = new Pessoa();
				person.setId(resultSet.getLong("id"));
				person.setCpf(resultSet.getString("cpf"));
				person.setNome(resultSet.getString("nome"));
				person.setLogradouro(resultSet.getString("logradouro"));
				person.setBairro(resultSet.getString("bairro"));
				person.setCidadeId(resultSet.getLong("cidade_id"));
				person.setCep(resultSet.getString("cep"));
				person.setNumero(resultSet.getString("numero"));

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return person;
	}

	@Override
	public Long create(final Pessoa entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO pessoa";
		sql += " (cpf, nome, logradouro,";
		sql += " numero, bairro, cep, cidade_id)";
		sql += "VALUES(?,?,?,?,?,?,?)";

		Long id = Long.valueOf(-1);

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getCpf());
			preparedStatement.setString(2, entity.getNome());
			preparedStatement.setString(3, entity.getLogradouro());
			preparedStatement.setString(4, entity.getNumero());
			preparedStatement.setString(5, entity.getBairro());
			preparedStatement.setString(6, entity.getCep());
			preparedStatement.setLong(7, entity.getCidadeId());

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
	public boolean update(final Pessoa entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE pessoa SET";
		sql += " cpf = ?,";
		sql += " nome = ?,";
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

			preparedStatement.setString(1, entity.getCpf());
			preparedStatement.setString(2, entity.getNome());
			preparedStatement.setString(3, entity.getLogradouro());
			preparedStatement.setString(4, entity.getNumero());
			preparedStatement.setString(5, entity.getBairro());
			preparedStatement.setString(6, entity.getCep());
			preparedStatement.setLong(7, entity.getCidadeId());
			preparedStatement.setLong(8, entity.getId());

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

		final String sql = "DELETE FROM pessoa WHERE id = ?;";

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
