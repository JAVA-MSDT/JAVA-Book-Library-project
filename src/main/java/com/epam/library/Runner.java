package com.epam.library;


import com.epam.library.entity.Book;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.model.builder.UserBuilder;
import com.epam.library.model.dao.DaoException;
import com.epam.library.model.dao.OrderDao;
import com.epam.library.util.constant.query.OrderQuery;
import com.epam.library.model.db.ConnectionPool;
import com.epam.library.model.service.*;
import com.epam.library.model.service.orderservice.user.UserOrderDisplay;
import com.epam.library.util.PropertiesExtractor;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        String CREATE_SQL_LOCATION = "src/main/sql/createTables.sql";
        String INSERT_INTO_TABLE = "src/main/sql/insertData.sql";
        try {
            String driver = PropertiesExtractor.getValueFromProperties("db.driver", "dataBase");
             Class.forName(driver);
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
             // statement.executeUpdate("DROP DATABASE " + "library");
              // statement.executeUpdate("CREATE DATABASE " + "library");
            System.out.println("Creating .....");
            statement.executeUpdate("USE " + "library");
            System.out.println("database using....");

             //  updateData(CREATE_SQL_LOCATION, statement);
            System.out.println("Creating tables Done Successfully!");

             //  updateData(INSERT_INTO_TABLE, statement);
            System.out.println("Data Inserted Successfully..!");

            System.out.println("==================================");
            System.out.println("Testing OrderDao");

            ServiceFactory serviceFactory = new ServiceFactory(connection);

            UserService userService = serviceFactory.getUserService();
            System.out.println("Select By email: " + userService.findByEmail("filter@auth.com"));
            List<User> users = userService.getAll();
            for (User u : users) {
                System.out.println(u);
            }

            System.out.println("===============================");
            BookService bookService = serviceFactory.getBookService();
            List<Book> books = bookService.getAll();
            for (Book b : books) {
                System.out.println(b);
            }


            System.out.println("===============================");

            OrderService orderService = serviceFactory.getOrderService();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date orderDate = Date.valueOf("2018-10-26");
            Date returningDate = Date.valueOf("2018-11-14");
            String mySqlOrderDate = simpleDateFormat.format(orderDate);
            String mySqlREturningDate = simpleDateFormat.format(returningDate);

            List<Order> orders = orderService.getAll();
            for (Order o : orders) {
                System.out.println(o);
            }

            ResultSet resultSet = statement.executeQuery(OrderQuery.SELECT_ORDER_FOR_USER);
            printTable(resultSet);
            System.out.println("============================");
            OrderDao orderDao = new OrderDao(connection);
            List<UserOrderDisplay> orderDisplayList = orderDao.userOrders();
            for (UserOrderDisplay display : orderDisplayList) {
                System.out.println(display);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.getMessage();
        } catch (DaoException e) {
            e.getLocalizedMessage();
        } catch (ClassNotFoundException e) {
            e.getCause();
        }
    }

    private static void updateData(String sqlLocation, Statement statement) throws SQLException {

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
            System.out.println("Var4");
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
            System.out.println("Var4");
        }
        return Collections.emptyList();
    }

}
