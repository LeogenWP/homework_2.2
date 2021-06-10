package org.leogenwp.repository.io;

import org.leogenwp.model.Label;
import org.leogenwp.repository.LabelRepository;
import org.leogenwp.utils.ConnectDB;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOLabelRepository  implements LabelRepository {

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList<>();
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id, description from labels")) {
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
        try(Connection conn= ConnectDB.getInstance().getConnection()){
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO labels (description) " + "VALUES ('" + label.getName() + "')",Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                label.setId(rs.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
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
        try(Connection conn= ConnectDB.getInstance().getConnection();
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
        try(Connection conn= ConnectDB.getInstance().getConnection();
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
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement()) {
            String sql = "delete from labels where id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
