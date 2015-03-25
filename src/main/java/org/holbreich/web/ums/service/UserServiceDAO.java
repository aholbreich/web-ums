package org.holbreich.web.ums.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.holbreich.web.ums.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceDAO extends NamedParameterJdbcDaoSupport {

	private static final String SELECT = "SELECT id, login, password FROM USER WHERE id = :id";

	@Autowired
	public UserServiceDAO(final DataSource ds) {
		setDataSource(ds);
	}

	public long createNewUser(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("login", user.getLogin());
		paramSource.addValue("password", user.getPassword());

		KeyHolder kh = new GeneratedKeyHolder();
		this.getNamedParameterJdbcTemplate().update(
				"INSERT INTO USER (login, password) VALUES(:login, :password)",
				paramSource, kh);
		return kh.getKey().longValue();
	}

	public User getUserById(long userId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", userId);
		return this.getNamedParameterJdbcTemplate().queryForObject(SELECT, paramSource, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User u  = new User();
				u.setId(rs.getLong("id"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				return u;
			}
			
		});//TODO

	}

}
