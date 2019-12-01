package games.battleship.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import games.battleship.constant.Config;
import games.battleship.model.Result;

/**
 * The class ResultDao is responsible for interaction with Results table in the
 * database.
 *
 */
public class ResultDao {

	/**
	 * This method fetches previous results of the game.
	 * 
	 * @return list of result object
	 */
	public List<Result> getResults() {
		List<Result> results = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + Config.MYSQL_HOST + ":" + Config.MYSQL_PORT + "/" + Config.RESULTS_DATABASE_NAME,
					Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select player1, player2, winner from Results");
			while (rs.next()) {
				results.add(new Result(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * 
	 * This method saves the results of the game.
	 * 
	 * @param player1 player 1 name
	 * @param player2 player 2 name
	 * @param winner  winner name
	 */
	public void saveResult(String player1, String player2, String winner) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + Config.MYSQL_HOST + ":" + Config.MYSQL_PORT + "/" + Config.RESULTS_DATABASE_NAME,
					Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("insert into Results (player1, player2, winner) values('" + player1 + "', '" + player2
					+ "', '" + winner + "')");
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This method deletes result on the basis of given parameters.
	 * 
	 * @param player1 player 1 name
	 * @param player2 player 2 name
	 */
	public void deleteResult(String player1, String player2) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://" + Config.MYSQL_HOST + ":" + Config.MYSQL_PORT + "/" + Config.RESULTS_DATABASE_NAME,
					Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("delete from Results where player1 = '" + player1 + "' and player2 = '" + player2 + "'");
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
