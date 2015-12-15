package com.mbds.first.controller;

import com.mbds.first.domain.ResultWrapperGuava;
import org.junit.Test;
import org.junit.runner.RunWith;


import javax.smartcardio.CardException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by marwen on 15/12/15.
 */

@RunWith(OnlyWithSmartCardReaderRunner.class)
public class CardControllerIntegrationTest {

    private CardController tested = new CardController();

    @Test
    public void souldReturnTerminalList() throws CardException {
        // when
        ResultWrapperGuava terminalList = tested.terminalList();
        //then
        assertThat(terminalList).isNotNull();
    }
}