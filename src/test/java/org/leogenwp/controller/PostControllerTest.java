package org.leogenwp.controller;

import org.junit.jupiter.api.Test;
import org.leogenwp.model.Post;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class PostControllerTest {

    @Test
    void save() {
        // give
        PostController postController_underTest = mock(PostController.class);
        Post post = new Post().setContent("Tom");
        // when
        when(postController_underTest.save("Tom")).thenReturn(post);

        //then
        assertEquals(post,postController_underTest.save("Tom"));

    }

    @Test
    void getAll() {
        // give
        PostController postController_underTest = mock(PostController.class);
        List<Post> posts = Arrays.asList(
                new Post(),
                new Post()
        );
        // when
        when(postController_underTest.getAll()).thenReturn(posts);

        //then
        assertEquals(posts,postController_underTest.getAll());
    }

    @Test
    void deleteById() {
        // given
        PostController postController_underTest = mock(PostController.class);
        // when
        postController_underTest.deleteById(1);
        // then
        verify(postController_underTest).deleteById(1);
    }

    @Test
    void getById() {
        // give
        PostController postController_underTest = mock(PostController.class);
        Post post = new Post().setId(1);
        // when
        when(postController_underTest.getById(1)).thenReturn(post);
        //then
        assertEquals(post,postController_underTest.getById(1));
    }

    @Test
    void updateById() {
        // give
        PostController postController_underTest = mock(PostController.class);
        // when
        postController_underTest.updateById( new BufferedReader(new InputStreamReader(System.in)));
        //then
        verify(postController_underTest).updateById(new BufferedReader(new InputStreamReader(System.in)));
    }
}