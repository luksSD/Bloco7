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

			final String sql = "select * from pessoa P inner join Usuario U on U.id = P.usuario_id";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Pessoa person = new Pessoa();
				person.setId(resultSet.getLong("id"));
				person.setCelular(resultSet.getString("celular"));
				person.setSenha(resultSet.getString("senha"));
				person.setEmail(resultSet.getString("email"));
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

			String sql = "select * from pessoa P inner join Usuario U on U.id = P.usuario_id";
			sql += " where U.id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				person = new Pessoa();
				person.setId(resultSet.getLong("id"));
				person.setCelular(resultSet.getString("celular"));
				person.setSenha(resultSet.getString("senha"));
				person.setEmail(resultSet.getString("email"));
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
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;

		final String sql = " INSERT INTO usuario (senha, email, celular) values (? , ? , ?);";

		final String sql2 = "INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, bairro, cidade_id ) values (? , ? , ? , ? , ? , ? );";

		Long id = Long.valueOf(-1);

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getSenha());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setString(3, entity.getCelular());

			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}

			connection.commit();

			preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

			preparedStatement2.setLong(1, id);
			preparedStatement2.setString(2, entity.getCpf());
			preparedStatement2.setString(3, entity.getNome());
			preparedStatement2.setString(4, entity.getLogradouro());
			preparedStatement2.setString(5, entity.getBairro());
			preparedStatement2.setLong(6, entity.getCidadeId());
			preparedStatement2.execute();
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

		String sql = "START TRANSACTION;";
		sql += " UPDATE usuario SET";
		sql += " senha = ?,";
		sql += " celular = ?,";
		sql += " where id = ?;";
		sql += " UPDATE pessoa SET";
		sql += " cpf = ?,";
		sql += " nome = ?,";
		sql += " logradouro = ?,";
		sql += " numero = ?,";
		sql += " bairro = ?,";
		sql += " cep = ?,";
		sql += " cidade_id = ?,";
		sql += " where id = ?;";
		sql += " COMMIT;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getSenha());
			preparedStatement.setString(2, entity.getCelular());
			preparedStatement.setLong(3, entity.getId());
			preparedStatement.setString(4, entity.getCpf());
			preparedStatement.setString(5, entity.getNome());
			preparedStatement.setString(6, entity.getLogradouro());
			preparedStatement.setString(7, entity.getNumero());
			preparedStatement.setString(8, entity.getBairro());
			preparedStatement.setString(9, entity.getCep());
			preparedStatement.setLong(10, entity.getCidadeId());
			preparedStatement.setLong(11, entity.getId());

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

		String sql = "START TRANSACTION;";
		sql += "DELETE FROM pessoa WHERE usuario_id = ?;";
		sql += "DELETE FROM usuario WHERE id = ?;";
		sql += "COMMIT;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);
			preparedStatement.setLong(2, id);

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
