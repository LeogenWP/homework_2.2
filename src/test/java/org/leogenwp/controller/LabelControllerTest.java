package org.leogenwp.controller;

import org.junit.jupiter.api.Test;
import org.leogenwp.model.Label;
import org.leogenwp.model.Post;
import org.leogenwp.repository.LabelRepository;
import org.leogenwp.service.LabelService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LabelControllerTest {

    @Test
    void CanSaveLabel() {
        // given
        LabelController labelController_underTest = new LabelController();
        //then
        assertEquals(Label.class,labelController_underTest.save("Table").getClass());
    }

    @Test
    void getAll() {
        // given
        LabelController labelController_underTest = mock(LabelController.class);
        List<Label> labels = Arrays.asList(
                new Label("Bom"),
                new Label("Tom")
        );
        // when
        when(labelController_underTest.getAll()).thenReturn(labels);

        //then
        assertEquals(labels,labelController_underTest.getAll());
    }

    @Test
    void deleteById() {
        // given
        LabelController labelController_underTest = mock(LabelController.class);
        // when
        labelController_underTest.deleteById(1);
        // then
        verify(labelController_underTest).deleteById(1);
    }

    @Test
    void getById() {
        // given
        LabelController labelController_underTest = mock(LabelController.class);
        Label label = new Label(1,"Bom");
        // when
        when(labelController_underTest.getById(1)).thenReturn(label);

        //then
        assertEquals(label,labelController_underTest.getById(1));
    }

    @Test
    void updateById() {
        // given
        LabelController labelController_underTest = mock(LabelController.class);
        Label label = new Label(1,"Bom");
        // when
        when(labelController_underTest.updateById(1,"Bom")).thenReturn(label);

        //then
        assertEquals(label,labelController_underTest.updateById(1,"Bom"));
    }
}