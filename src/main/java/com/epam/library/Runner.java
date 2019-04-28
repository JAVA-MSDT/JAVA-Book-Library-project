package com.epam.library;


import com.epam.library.entity.User;
import com.epam.library.model.builder.UserBuilder;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        String CREATE_SQL_LOCATION = "src/main/sql/createTables.sql";
         String INSERT_INTO_TABLE = "src/main/sql/insertData.sql";
      /*  try {
           // Class.forName(DBInfo.DB_DRIVER);
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
             statement.executeUpdate("DROP DATABASE " + DBInfo.DB_NAME);
             statement.executeUpdate("CREATE DATABASE " + DBInfo.DB_NAME);
            System.out.println("Creating .....");
            statement.executeUpdate("USE " + DBInfo.DB_NAME);
            System.out.println("database using....");

              updateData(DBInfo.CREATE_SQL_LOCATION, statement);
            System.out.println("Creating tables Done Successfully!");

              updateData(DBInfo.INSERT_INTO_TABLE, statement);
            System.out.println("Data Inserted Successfully..!");

            System.out.println("==================================");
            System.out.println("Testing OrderDao");


            UserDao userDao = new DaoFactory(connection).getUserDao();
            List<User> users = userDao.getAll();
            for(User u : users){
                System.out.println(u);
            }


            UserService userService = new ServiceFactory(connection).getUserService();
            System.out.println("===============================");
            BookDao bookDao = new DaoFactory(connection).getBookDao();

            System.out.println(bookDao.getById(4));
            List<Book> books = bookDao.getAll();
            for (Book b : books){
                System.out.println(b);
            }


            System.out.println("===============================");

            OrderDao orderDao = new DaoFactory(connection).getOrderDao();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date orderDate = Date.valueOf("2018-10-26");
            Date returningDate = Date.valueOf("2018-11-14");
            String mySqlOrderDate = simpleDateFormat.format(orderDate);
            String mySqlREturningDate = simpleDateFormat.format(returningDate);

            List<Order> orders = orderDao.getAll();
            for(Order o : orders){
                System.out.println(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.getCause();
        }
*/
    }

    public static void updateData(String sqlLocation, Statement statement) throws SQLException {

        File file = new File(sqlLocation);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            StringBuilder buffer = new StringBuilder();
            String read = reader.readLine();
            while (read != null) {
                buffer.append(read);
                read = reader.readLine();
            }
            String[] state = buffer.toString().split(";");
            for (String st : state) {
                statement.executeUpdate(st);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }

    }

    public static void printTable(ResultSet myResult) {
        try {
            System.out.println();
            ResultSetMetaData rsmd = myResult.getMetaData();

            int i;
            for (i = 1; i <= rsmd.getColumnCount(); ++i) {
                System.out.print(" " + rsmd.getColumnName(i) + "\t");
            }
            while (myResult.next()) {
                System.out.println("\n");
                for (i = 1; i <= rsmd.getColumnCount(); ++i) {
                    System.out.print("\t" + myResult.getString(i) + " ");
                }
            }
        } catch (SQLException var4) {

        }

    }

    public static List<User> getUsers(ResultSet myResult) {
        UserBuilder userBuilder = new UserBuilder();
        List<User> users = new ArrayList<>();
        int counter = 1;
        try {
            while (myResult.next()) {
                System.out.println("Check Records" + counter);
                User user = userBuilder.build(myResult);
                //users.add(user);
                counter++;
            }

        } catch (SQLException var4) {

        }
        return Collections.emptyList();
    }

}
