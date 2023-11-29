package com.example.testtinkoff.repository;

import com.example.testtinkoff.entity.Requests;
import com.example.testtinkoff.forms.FormRequest;
import com.example.testtinkoff.forms.FormResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class TranslationsRepository {

    @Autowired
    private DataSource dataSource;

    public void save(FormResponse formResponse, Requests requests, int id){
        String[] s = requests.getInput().split(" ");

        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statementTranslations = connection.prepareStatement("INSERT INTO Translations(request_id, word, translation) " +
                    "VALUES (?,?,?)");
            for (int i = 0; i < formResponse.getListWords().size(); i++) {
                statementTranslations.setInt(1, id);
                statementTranslations.setString(2, s[i]);
                statementTranslations.setString(3, formResponse.getListWords().get(i));
                statementTranslations.addBatch();
            }
            statementTranslations.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
