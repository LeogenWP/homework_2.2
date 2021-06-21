package org.leogenwp.controller;

import org.junit.jupiter.api.Test;
import org.leogenwp.model.Post;
import org.leogenwp.model.Writer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WriterControllerTest {

    @Test
    void save() {
        // give
        WriterController writerController_underTest = mock(WriterController.class);
        Writer writer = new Writer().setFirstName("Bob").setLastName("White");
        // when
        when(writerController_underTest.save("Bob","White")).thenReturn(writer);

        //then
        assertEquals(writer,writerController_underTest.save("Bob","White"));

    }

    @Test
    void getAll() {
        // give
        WriterController writerController_underTest = mock(WriterController.class);
        List<Writer> writers = Arrays.asList(
                new Writer(),
                new Writer()
        );
        // when
        when(writerController_underTest.getAll()).thenReturn(writers);

        //then
        assertEquals(writers,writerController_underTest.getAll());
    }

    @Test
    void deleteById() {
        // given
        WriterController writerController_underTest = mock(WriterController.class);
        // when
        writerController_underTest.deleteById(1);
        // then
        verify(writerController_underTest).deleteById(1);
    }

    @Test
    void getById() {
        // give
        WriterController writerController_underTest = mock(WriterController.class);
        Writer writer = new Writer().setId(1);
        // when
        when(writerController_underTest.getById(1)).thenReturn(writer);
        //then
        assertEquals(writer,writerController_underTest.getById(1));
    }

    @Test
    void updateById() {
        // give
        WriterController writerController_underTest = mock(WriterController.class);
        // when
        writerController_underTest.updateById( new BufferedReader(new InputStreamReader(System.in)));
        //then
        verify(writerController_underTest).updateById(new BufferedReader(new InputStreamReader(System.in)));
    }
}