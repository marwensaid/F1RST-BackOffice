package com.mbds.first.controller;

import com.mbds.first.domain.ResultWrapperGuava;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import java.util.List;

/**
 * Created by marwen on 15/12/15.
 */

@RestController
public class CardController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @RequestMapping(value = "/terminal", produces = "application/json")
    public ResultWrapperGuava terminalList() throws CardException {
        TerminalFactory terminalFactory = TerminalFactory.getDefault();
        List<CardTerminal> cardTerminalList = terminalFactory.terminals().list();
        LOGGER.info("Terminal Connected {}", cardTerminalList);
        return new ResultWrapperGuava(cardTerminalList.toString());
    }

}
