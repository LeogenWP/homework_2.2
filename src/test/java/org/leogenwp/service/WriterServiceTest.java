package org.leogenwp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.leogenwp.model.Post;
import org.leogenwp.model.Writer;
import org.leogenwp.repository.PostRepository;
import org.leogenwp.repository.WriterRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WriterServiceTest {

    @Test
    void canSaveWriter() {
        // given
        Writer writer = new Writer("Bob","White");
        WriterRepository writerRepository = mock(WriterRepository.class);
        WriterService writerService_underTest = new WriterService(writerRepository);

        // when
        when(writerRepository.save(writer)).thenReturn(writer);

        //then
        assertEquals(writer,writerService_underTest.save(writer));
    }

    @Test
    void canGetAllWriters() {
        // given
        List<Writer> writers = Arrays.asList(
                new Writer("Bob","White"),
                new Writer("Rob","Black"),
                new Writer("Bob","White"));
        WriterRepository writerRepository = mock(WriterRepository.class);
        WriterService writerService_underTest = new WriterService(writerRepository);

        // when
        when(writerRepository.getAll()).thenReturn(writers);

        //then
        assertEquals(writers,writerService_underTest.getAll());
    }

    @Test
    void canDeleteWriterById() {
        // given
        WriterService writerService_underTest = mock(WriterService.class);
        // when
        writerService_underTest.deleteById(1);
        // then
        verify(writerService_underTest).deleteById(1);
    }

    @Test
    void canGetWriterById() {
        // given
        Writer writer = new Writer("Bob","White").setId(1);
        WriterRepository writerRepository = mock(WriterRepository.class);
        WriterService writerService_underTest = new WriterService(writerRepository);

        // when
        when(writerRepository.getById(1)).thenReturn(writer);

        //then
        assertEquals(writer,writerService_underTest.getById(1));
    }

    @Test
    void canUpdateWriter() {
        // given
        Writer writer = new Writer("Bob","White").setId(1);
        WriterRepository writerRepository = mock(WriterRepository.class);
        WriterService writerService_underTest = new WriterService(writerRepository);

        // when
        when(writerRepository.update(writer)).thenReturn(writer);

        //then
        assertEquals(writer,writerService_underTest.update(writer));
    }
}