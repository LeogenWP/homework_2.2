package org.leogenwp.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leogenwp.controller.LabelController;
import org.leogenwp.repository.LabelRepository;
import org.leogenwp.repository.io.JavaIOLabelRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.leogenwp.model.Label;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class LabelServiceTest {

    @Test
    void CanSaveLabel() {
        // given
        Label label = new Label("Table");
        LabelRepository labelRepository = mock(LabelRepository.class);
        LabelService labelService_underTest = new LabelService(labelRepository);

        // when
        when(labelRepository.save(label)).thenReturn(label);

        //then
        assertEquals(label,labelService_underTest.save(label));
    }

    @Test
    void canGetAllLabels() {
        // given
        List<Label> labels = Arrays.asList(
                new Label("Table"),
                new Label("Mable"),
                new Label("Cable"));
        LabelRepository labelRepository = mock(LabelRepository.class);
        LabelService labelService_underTest = new LabelService(labelRepository);

        // when
        when(labelRepository.getAll()).thenReturn(labels);
        // then
        assertEquals(labels,labelService_underTest.getAll());
    }

    @Test
    void deleteById() {
        // given
        LabelService labelService_underTest = mock(LabelService.class);
        // when
        labelService_underTest.deleteById(1);
        // then
        verify(labelService_underTest).deleteById(1);

    }

    @Test
    void canGetLabelById() {
        // given
        Label label = new Label("Table").setId(1);
        LabelRepository labelRepository = mock(LabelRepository.class);
        LabelService labelService_underTest = new LabelService(labelRepository);

        // when
        when(labelRepository.getById(1)).thenReturn(label);

        //then
        assertEquals(label,labelService_underTest.getById(1));

    }

    @Test
    void canUpdateLabel() {
        // given
        Label label = new Label("Table").setId(1);
        LabelRepository labelRepository = mock(LabelRepository.class);
        LabelService labelService_underTest = new LabelService(labelRepository);

        // when
        when(labelRepository.update(label)).thenReturn(label);

        //then
        assertEquals(label,labelService_underTest.update(label));
    }
}