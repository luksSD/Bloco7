package br.fai.bloco7.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import br.fai.bloco7.db.connection.ConnectionFactory;
import br.fai.bloco7.db.dao.CidadeDao;
import br.fai.bloco7.model.Cidade;

@Repository
public class CidadeDaoImpl implements CidadeDao {

	@Override
	public Cidade readById(final Long id) {
		Cidade cidade = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM cidade where id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				cidade = new Cidade();
				cidade.setId(resultSet.getLong("id"));
				cidade.setEstado(resultSet.getString("estado"));
				cidade.setNome(resultSet.getString("nome"));

			}

		} catch (final Exception e) {

		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return cidade;
	}

}
