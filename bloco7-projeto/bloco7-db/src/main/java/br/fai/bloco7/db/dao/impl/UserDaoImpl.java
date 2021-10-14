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
import br.fai.bloco7.db.dao.UserDao;
import br.fai.bloco7.model.Usuario;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public List<Usuario> readAll() {

		final List<Usuario> users = new ArrayList<Usuario>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM usuario";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Usuario user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setCelular(resultSet.getString("celular"));
				user.setSenha(resultSet.getString("senha"));
				user.setEmail(resultSet.getString("email"));
				user.setPessoaId(resultSet.getLong("pessoa_id"));

				users.add(user);

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return users;
	}

	@Override
	public Usuario readById(final Long id) {

		Usuario user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM usuario where id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setCelular(resultSet.getString("celular"));
				user.setSenha(resultSet.getString("senha"));
				user.setEmail(resultSet.getString("email"));
				user.setPessoaId(resultSet.getLong("pessoa_id"));

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return user;
	}

	@Override
	public Long create(final Usuario entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO usuario";
		sql += " (nome_usuario, senha, nome_completo,";
		sql += " email, tipo, esta_ativo, data_nascimento,";
		sql += "ultimo_acesso, criado_em)";
		sql += "VALUES(?,?,?,?,?,?,?,?,?)";

		Long id = Long.valueOf(-1);

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getCelular());
			preparedStatement.setString(2, entity.getSenha());
			preparedStatement.setString(3, entity.getEmail());

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
	public boolean update(final Usuario entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE usuario SET ";
		sql += " nome_completo = ?,";
		sql += " email = ?,";
		sql += " where id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getCelular());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setLong(3, entity.getId());

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

		final String sql = "DELETE FROM usuario WHERE id = ?;";

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
	public Usuario authentication(Usuario entity) {
		
		Usuario user = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from usuario where ";
		sql += " email = ?,";
		sql += " and senha = ?,";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getEmail());
			preparedStatement.setString(2, entity.getSenha());

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setEmail(resultSet.getString("email"));
				user.setSenha(resultSet.getString("senha"));

			}
			return user;
		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return user;
	}

}
