package org.holbreich.web.ums.db;

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

	private static final String INSERT = "INSERT INTO pligg_users (user_login, user_pass, user_email, user_level) VALUES(:login, :password, :email, 'normal')";
	private static final String SELECT_BY_ID = "SELECT user_id, user_login, user_pass FROM pligg_users WHERE user_id = :id";
	private static final String SELECT_BY_LOGIN = "SELECT user_id, user_login, user_pass FROM pligg_users WHERE user_login = :login";

	@Autowired
	public UserServiceDAO(final DataSource ds) {
		setDataSource(ds);
	}

	public long createNewUser(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("login", user.getLogin());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("email", user.getEmail());

		KeyHolder kh = new GeneratedKeyHolder();
		this.getNamedParameterJdbcTemplate().update(INSERT, paramSource, kh);
		return kh.getKey().longValue();
	}

	public User getUserById(long userId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", userId);
		return this.getNamedParameterJdbcTemplate().queryForObject(SELECT_BY_ID, paramSource, getUserRowMapper());
	}

	public User getUserByLogin(String login) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("login", login);
		return this.getNamedParameterJdbcTemplate().queryForObject(SELECT_BY_LOGIN, paramSource, getUserRowMapper());

	}

	private RowMapper<User> getUserRowMapper() {
		return new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				return u;
			}

		};
	}

}
