package org.leogenwp.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leogenwp.repository.LabelRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.leogenwp.model.Label;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class LabelServiceTest {

    @Mock
    private LabelRepository labelRepository;
    private static AutoCloseable autoCloseable;
    private LabelService labelService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        labelService = new LabelService(labelRepository);
    }

    @AfterAll
    static void afterAll() throws  Exception {
        autoCloseable.close();
    }

    @Test
    void CanSaveLabel() {
        // given
        Label label = new Label("Table");

        // when
        labelService.save(label);

        // then
        ArgumentCaptor<Label>  labelArgumentCaptor =
                ArgumentCaptor.forClass(Label.class);

        verify(labelRepository).save(labelArgumentCaptor.capture());

        Label capturedLabel = labelArgumentCaptor.getValue();

        assertEquals(label,capturedLabel);
    }

    @Test
    void canGetAllLabels() {
        // when
        labelService.getAll();
        // then
        verify(labelRepository).getAll();
    }

    @Test
    @Disabled
    void deleteById() {

    }

    @Test
    @Disabled
    void getById() {

    }

    @Test
    @Disabled
    void updateById() {

    }
}