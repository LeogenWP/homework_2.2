package org.leogenwp.repository.io;

import org.leogenwp.model.Label;
import org.leogenwp.repository.LabelRepository;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOLabelRepository  implements LabelRepository {
    private final String url = "jdbc:mysql://localhost:3306/writer";
    private final String username = "root";
    private final String password = "admin";

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id, description from task")) {
            while ( rs.next() ) {
                labels.add(new Label(rs.getInt("id"),rs.getString("description")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return labels;
    }

    @Override
    public Label save(Label label) {
        try(Connection conn=DriverManager.getConnection(
                url,username,password)){
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO writers (description) " + "VALUES ('" + label.getName() + "')");
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    label.setId(generatedKeys.getInt("id"));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch(Exception e){ System.out.println(e);}
        return label;
    }

    @Override
    public Label getById(Integer id) {
       Label label = new Label();
        String sql = "select   id, description " +
                " from labels " +
                " where id = " + id ;
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)) {
            while ( rs.next() ) {
                label.setId(rs.getInt("id"));
                label.setName(rs.getString("description"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return label;
    }

    @Override
    public Label update(Label label) {
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {
            String sql = "UPDATE labels SET description = " + label.getName() +" WHERE id = " + label.getId();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {
            String sql = "delete from labels where id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
