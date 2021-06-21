package org.leogenwp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.leogenwp.model.Label;
import org.leogenwp.model.Post;
import org.leogenwp.repository.LabelRepository;
import org.leogenwp.repository.PostRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Test
    void canSavePost() {
        // given
        Post post = new Post("Content");
        PostRepository postRepository = mock(PostRepository.class);
        PostService postService_underTest = new PostService(postRepository);

        // when
        when(postRepository.save(post)).thenReturn(post);

        //then
        assertEquals(post,postService_underTest.save(post));
    }

    @Test
    void canGetAllPosts() {
        // given
        List<Post> posts = Arrays.asList(
                new Post("Content1"),
                new Post("Content2"),
                new Post("Content3"));

        PostRepository postRepository = mock(PostRepository.class);
        PostService postService_underTest = new PostService(postRepository);

        // when
        when(postRepository.getAll()).thenReturn(posts);

        //then
        assertEquals(posts,postService_underTest.getAll());
    }

    @Test
    void canDeletePostById() {
        // given
        PostService postService_underTest = mock(PostService.class);
        // when
        postService_underTest.deleteById(1);
        // then
        verify(postService_underTest).deleteById(1);
    }

    @Test
    void CanGetPost() {
        // given
        Post post = new Post("Content").setId(1);
        PostRepository postRepository = mock(PostRepository.class);
        PostService postService_underTest = new PostService(postRepository);

        // when
        when(postRepository.getById(1)).thenReturn(post);

        //then
        assertEquals(post,postService_underTest.getById(1));
    }

    @Test
    void canUpdatePost() {
        // given
        Post post = new Post("Content").setId(1);
        PostRepository postRepository = mock(PostRepository.class);
        PostService postService_underTest = new PostService(postRepository);

        // when
        when(postRepository.update(post)).thenReturn(post);

        //then
        assertEquals(post,postService_underTest.update(post));
    }
}