package com.example.testtinkoff.repository;

import com.example.testtinkoff.entity.Requests;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;


@org.springframework.stereotype.Repository
public class RequestsRepository {

    @Autowired
    private DataSource dataSource;

    public int saveInfo(Requests requests) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statementRequest = connection.prepareStatement("INSERT INTO Requests(input_data, output_data, request_time, ip_address,parameters) " +
                    "VALUES (?,?,?,?,?)");

            statementRequest.setString(1, requests.getInput());
            statementRequest.setString(2, requests.getOutput());
            statementRequest.setObject(3, requests.getRequestTime());
            statementRequest.setString(4, requests.getIPAddress());
            statementRequest.setString(5, requests.getParameters());

            int executed = statementRequest.executeUpdate();

            int id = 0;
            if (executed > 0) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid()");
                if (resultSet.next()){
                    id = resultSet.getInt("last_insert_rowid()");
                }
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
