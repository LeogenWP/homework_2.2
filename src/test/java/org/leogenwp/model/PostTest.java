package org.leogenwp.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void canGetId() {
        // given
        Post post = new Post();
        // when
        post.setId(1);
        // then
        assertEquals(1,post.getId());
    }

    @Test
    void canSetId() {
        // given
        Post post = new Post();
        // when
        post.setId(1);
        // then
        assertEquals(1,post.getId());
    }

    @Test
    void canGetContent() {
        // given
        Post post = new Post();
        // when
        post.setContent("content");
        // then
        assertEquals("content",post.getContent());
    }

    @Test
    void canSetContent() {
        // given
        Post post = new Post();
        // when
        post.setContent("content");
        // then
        assertEquals("content",post.getContent());
    }

    @Test
    void getCreated() {
        // given
        Post post = new Post();
        // when
        post.setCreated("2021");
        // then
        assertEquals("2021",post.getCreated());
    }

    @Test
    void setCreated() {
        // given
        Post post = new Post();
        // when
        post.setCreated("2021");
        // then
        assertEquals("2021",post.getCreated());
    }

    @Test
    void getUpdated() {
        // given
        Post post = new Post();
        // when
        post.setUpdated("2021");
        // then
        assertEquals("2021",post.getUpdated());
    }

    @Test
    void setUpdated() {
        // given
        Post post = new Post();
        // when
        post.setUpdated("2021");
        // then
        assertEquals("2021",post.getUpdated());
    }

    @Test
    void getLabels() {
        // given
        Post post = new Post();
        // when
        post.addLabel(new Label(1,"Bom"));
        post.addLabel(new Label(2,"Tom"));
        // then
        assertEquals(2,post.getLabels().size());
    }

    @Test
    void getPostStatus() {
        // given
        Post post = new Post();
        // when
        post.setPostStatus(PostStatus.ACTIVE);
        // then
        assertEquals(PostStatus.ACTIVE,post.getPostStatus());
    }

    @Test
    void setPostStatus() {
        // given
        Post post = new Post();
        // when
        post.setPostStatus(PostStatus.ACTIVE);
        // then
        assertEquals(PostStatus.ACTIVE,post.getPostStatus());
    }

    @Test
    void addLabel() {
        // given
        Post post = new Post();
        // when
        post.addLabel(new Label(1,"Bom"));
        post.addLabel(new Label(2,"Tom"));
        // then
        assertEquals(2,post.getLabels().size());
    }

    @Test
    void removeLabel() {
        // given
        Post post = new Post();
        // when
        post.addLabel(new Label(1,"Bom"));
        post.addLabel(new Label(2,"Tom"));
        post.removeLabel(1);
        // then
        assertEquals(1,post.getLabels().size());
    }
}