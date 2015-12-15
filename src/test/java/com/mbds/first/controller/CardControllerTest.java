package com.mbds.first.controller;

import com.mbds.first.domain.ResultWrapperGuava;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.smartcardio.CardException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by marwen on 15/12/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class CardControllerTest {

    private static final String TERMINALS = "";

    @InjectMocks
    private CardController tested;

    @Mock
    private CardController cardControllerMock;

    @Test
    public void testTerminalList() throws CardException {
/*        // given
        when(cardControllerMock.terminalList()).thenReturn(testTerminalList());

        //when
        ResultWrapperGuava actual = tested.terminalList();

        //then
        assertThat(actual).isNotNull();*/
    }


}