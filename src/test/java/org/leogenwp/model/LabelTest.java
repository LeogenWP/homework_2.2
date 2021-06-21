package org.leogenwp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelTest {

    @Test
    void canGetId() {
        // given
        Label label = new Label(1,"Test");
        // then
        assertEquals(1,label.getId());
    }

    @Test
    void canSetId() {
        // given
        Label label = new Label();
        // when
        label.setId(1);
        // then
        assertEquals(1,label.getId());
    }

    @Test
    void canGetName() {
        // given
        Label label = new Label(1,"Test");
        // then
        assertEquals("Test",label.getName());
    }

    @Test
    void canSetName() {
        // given
        Label label = new Label();
        // when
        label.setName("Test1");
        // then
        assertEquals("Test1",label.getName());
    }
}