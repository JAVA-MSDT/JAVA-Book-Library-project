package com.javamsdt.library.model.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.javamsdt.library.entity.User;
import com.javamsdt.library.model.builder.UserBuilder;
import com.javamsdt.library.util.MD5Encrypt;
import com.javamsdt.library.util.constant.query.UserQuery;
import com.javamsdt.library.util.validate.ArgumentValidator;

public class UserDao extends AbstractDao<User> {

	public UserDao(final Connection connection) {
		super(connection);
	}

	public UserDao() {

	}

	@Override

	public Optional<User> getById(final long id) throws DaoException {

		return executeSingleResponseQuery(UserQuery.SELECT_USER_BY_ID, new UserBuilder(), String.valueOf(id));
	}

	@Override
	public List<User> getAll() throws DaoException {
		return executeQuery(UserQuery.SELECT_ALL_USERS, new UserBuilder());
	}

	/**
	 * @param item the user to be saved
	 * @throws DaoException if something went wrong
	 */
	@Override
	public void save(final User item) throws DaoException {
		ArgumentValidator.checkForNull(item, "User item in save method at UserDao class can not be null");

		String encryptedPassword = MD5Encrypt.encrypt(item.getPassword());
		String[] userInfo = { item.getName(), item.getLastName(), item.getEmail(), item.getLogin(), encryptedPassword };

		executeUpdate(UserQuery.INSERT_USER, userInfo);
	}

	@Override
	public void removeById(final long id) throws DaoException {
		executeUpdate(UserQuery.DELETE_USER, String.valueOf(id));
	}

	/**
	 * @param item user to be update by the Librarian.
	 * @throws DaoException
	 */
	@Override
	public void update(final User item) throws DaoException {
		ArgumentValidator.checkForNull(item, "User item in update method at UserDao class can not be null");

		String blockStatues = String.valueOf(item.isBlocked());
		String[] userInfo = { item.getName(), item.getLastName(), item.getEmail(), item.getLogin(),
				item.getRole().name(), blockStatues, String.valueOf(item.getId()) };

		executeUpdate(UserQuery.UPDATE_USER, userInfo);

	}

	/**
	 * @param login    of the user
	 * @param password of the user
	 * @return userOptional
	 * @throws DaoException
	 */
	public Optional<User> findByLoginAndPassword(final String login, final String password) throws DaoException {
		ArgumentValidator.checkForNullOrEmptyString(login,
				"Not allow for a null or empty login in findByLoginAndPassword at UserDao class");
		ArgumentValidator.checkForNullOrEmptyString(login,
				"Not allow for a null or empty password in findByLoginAndPassword at UserDao class");
		String encryptedPassword = MD5Encrypt.encrypt(password);
		System.out.println(this.getClass().getSimpleName() + ", encryptedPassword: " + encryptedPassword + " Password: "
				+ password);
		return executeSingleResponseQuery(UserQuery.SELECT_USER_BY_LOGIN_PASSWORD, new UserBuilder(), login,
				encryptedPassword);
	}

	public Optional<User> findByLogin(final String login) throws DaoException {
		ArgumentValidator.checkForNullOrEmptyString(login,
				"Not allow for null or empty value in findByLogin " + "method at userDao Class");

		return executeSingleResponseQuery(UserQuery.SELECT_USER_BY_LOGIN, new UserBuilder(), login);
	}

	public Optional<User> findByEmail(final String email) throws DaoException {
		return executeSingleResponseQuery(UserQuery.SELECT_USER_BY_EMAIL, new UserBuilder(), email);
	}

	// Librarian Query
	/**
	 * @return list of user by the role Reader
	 * @throws DaoException if something wrong happens while executing the query
	 */
	public List<User> findAllWhereRoleReader() throws DaoException {
		return executeQuery(UserQuery.SELECT_USER_BY_READER_ROLE, new UserBuilder());
	}

	public List<User> sortUsersByName() throws DaoException {
		return executeQuery(UserQuery.SELECT_ALL_USERS_SORTING_BY_NAME, new UserBuilder());
	}

	public List<User> sortUsersByEmail() throws DaoException {
		return executeQuery(UserQuery.SELECT_ALL_USERS_SORTING_BY_EMAIL, new UserBuilder());
	}

	// Admin Query
	public List<User> adminSortUsersByName() throws DaoException {
		return executeQuery(UserQuery.SELECT_ALL_SORTING_BY_NAME_FOR_ADMIN, new UserBuilder());
	}

	public List<User> adminSortUsersByEmail() throws DaoException {
		return executeQuery(UserQuery.SELECT_ALL_SORTING_BY_EMAIL_FOR_ADMIN, new UserBuilder());
	}

	public void updateRole(final Long id, final String role) throws DaoException {
		executeUpdate(UserQuery.UPDATE_USER_ROLE, role, String.valueOf(id));
	}
}
