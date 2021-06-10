package org.leogenwp.repository.io;

import org.leogenwp.model.Label;
import org.leogenwp.model.Post;
import org.leogenwp.model.PostStatus;
import org.leogenwp.model.Writer;
import org.leogenwp.repository.LabelRepository;
import org.leogenwp.repository.WriterRepository;
import org.leogenwp.utils.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JavaIOWriterRepository implements WriterRepository {
    private final LabelRepository labelRepository = new JavaIOLabelRepository();

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from writers")) {
            while ( rs.next() ) {
                Writer writer = new Writer(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"));
                writer.setPosts(getWriterPosts(rs.getInt("id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writers ;
    }



    @Override
    public Writer save(Writer writer) {
        try(Connection conn= ConnectDB.getInstance().getConnection()){
            Statement statement = conn.createStatement();
            String values = "VALUES ('" + writer.getFirstName() + "', '"+ writer.getLastName() + "')";
            statement.executeUpdate("INSERT INTO writers (first_name,last_name) " + values,Statement.RETURN_GENERATED_KEYS);
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    writer.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch(Exception e){ System.out.println(e);}
        return writer;
    }

    @Override
    public Writer getById(Integer writerId) {
        Writer writer = new Writer();
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from writers where id = " + writerId)) {
            while ( rs.next() ) {
                 writer = new Writer(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"));
                writer.setPosts(getWriterPosts(rs.getInt("id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer ;
    }



    @Override
    public Writer update(Writer writer) {
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement()) {
            String sql = "UPDATE writers SET first_name = " + writer.getFirstName() +", last_name = "+writer.getLastName()+" WHERE id = " + writer.getId();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement()) {
            String sql = "UPDATE posts SET writer_id = " + writer.getId() +"  WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for(Post post : writer.getPosts()) {
                preparedStatement.setInt(1,post.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writer;
    }




    @Override
    public void deleteById(Integer id) {
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement()) {
            String sql = "delete from writers where id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement()) {
            String sql = "delete writer_id from posts where writer_id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Post> getWriterPosts(Integer writerId) {
        List<Post> posts = new ArrayList<>();

        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement()
        ) {
            String sql = "SELECT id,content,created,updated,post_status " +
                    "FROM posts where writer_id = " + writerId;
            ResultSet rs = statement.executeQuery(sql);

            while ( rs.next() ) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setContent(rs.getString("content"));
                post.setCreated(rs.getString("created"));
                post.setUpdated(rs.getString("updated"));
                if (rs.getString("post_status").equals("ACTIVE")) {
                    post.setPostStatus(PostStatus.ACTIVE);
                } else if (rs.getString("post_status").equals("UNDER_REVIEW")) {
                    post.setPostStatus(PostStatus.UNDER_REVIEW);
                } else if (rs.getString("post_status").equals("DELETED")) {
                    post.setPostStatus(PostStatus.DELETED);
                }

                post.setLabels(getPostLabels(rs.getInt("id")));
                posts.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return posts;
    }

    private List<Label> getPostLabels(Integer postId) {
        List<Label> labels = new ArrayList<>();
        try(Connection conn= ConnectDB.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT label_id FROM post_labels WHERE post_id = " + postId )) {
            while ( rs.next() ) {
                labels.add(labelRepository.getById(rs.getInt("label_id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return labels;
    }


}

