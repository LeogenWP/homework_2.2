package org.leogenwp.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    @Disabled
    void deleteById() {

    }

    @Test
    void canGetById() {
        Label label = new Label("Table").setId(1);
        LabelRepository labelRepository = mock(LabelRepository.class);
        LabelService labelService_underTest = new LabelService(labelRepository);

        // when
        when(labelRepository.getById(1)).thenReturn(label);

        //then
        assertEquals(label,labelService_underTest.getById(1));

    }

    @Test
    void canUpdateById() {
        Label label = new Label("Table").setId(1);
        LabelRepository labelRepository = mock(LabelRepository.class);
        LabelService labelService_underTest = new LabelService(labelRepository);

        // when
        when(labelRepository.update(label)).thenReturn(label);

        //then
        assertEquals(label,labelService_underTest.updateById(label));
    }
}