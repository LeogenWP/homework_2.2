package org.leogenwp.model;

import java.util.ArrayList;
import java.util.List;

public class Writer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Post> posts;

    public int getId() {
        return id;
    }

    public Writer setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Writer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Writer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Writer setPosts(List<Post> posts) {
        this.posts = posts;
        return this;
    }

    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        posts = new ArrayList<>();
    }

    public Writer() {
    }

    public Writer(Integer id,String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addPost(Post post){
        this.posts.add(post);
    }
    public void removePost(Integer id) {
        for (int i = 0; i < this.posts.size(); i++) {
            if (id == this.posts.get(i).getId()) {
                this.posts.remove(i);
                break;
            }
        }
    }
}
