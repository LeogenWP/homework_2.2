package org.leogenwp.repository.io;

import org.leogenwp.model.Label;
import org.leogenwp.model.Post;
import org.leogenwp.model.PostStatus;
import org.leogenwp.repository.LabelRepository;
import org.leogenwp.repository.PostRepository;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JavaIOPostRepository implements PostRepository {
    private String url = "jdbc:mysql://localhost:3306/writer";
    private String username = "root";
    private String password = "admin";
    private LabelRepository labelRepository = new JavaIOLabelRepository();
    private final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()
            ) {
            String sql = "SELECT id,content,created,updated,post_status " +
                    "FROM posts";
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

    @Override
    public Post save(Post post) {
        Date now = new Date();
        String strDate = sdfDate.format(now);
        post.setCreated(strDate);
        post.setUpdated(strDate);
        post.setPostStatus(PostStatus.ACTIVE);

        try(Connection conn=DriverManager.getConnection(
                url,username,password)){
            Statement statement = conn.createStatement();
            String values = "VALUES ('" + post.getContent() + "', '"+ post.getCreated() + "','"+post.getUpdated()+"','"+post.getPostStatus()+"')";
            statement.executeUpdate("INSERT INTO posts (content,created,updated,post_status) " + values);
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt("id"));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch(Exception e){ System.out.println(e);}
        return post;
    }

    @Override
    public Post getById(Integer postId) {
        Post post = new Post();
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()
        ) {
            String sql = "SELECT id,content,created,updated,post_status " +
                    "FROM posts where id = " + postId;
            ResultSet rs = statement.executeQuery(sql);
            while ( rs.next() ) {
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return post;
    }

    @Override
    public Post update(Post post) {
        Date now = new Date();
        String strDate = sdfDate.format(now);
        post.setUpdated(strDate);

        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {
            String sql = "UPDATE posts SET content = '"+post.getContent()+
                    "' ,created = '"+post.getCreated() +
                    "',updated = "+post.getUpdated() +
                    ",post_status = '" + post.getPostStatus() + "' WHERE id = " + post.getId();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {
            statement.executeUpdate("delete from posts_labels where id = " + post.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(Label label : post.getLabels()) {
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();) {
                statement.executeUpdate("INSERT INTO posts_labels (post_id,label_id) " + "VALUES (" + post.getId() + ", " + label.getId() + ")");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return post;
    }

    @Override
    public void deleteById (Integer id){
        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {
            statement.executeUpdate("delete from posts_labels where id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement()) {
            statement.executeUpdate("delete from posts where id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Label> getPostLabels(Integer postId) {
        List<Label> labels = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url);
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
