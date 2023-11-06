package com.udacity.timezones.service;

import com.udacity.timezones.client.ChargeUserApiHttpClient;
import com.udacity.timezones.model.TicketItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ChargeServiceTest {

    ChargeService chargeService;

    List<TicketItem> ticketItemList;

    @Mock
    ChargeUserApiHttpClient chargeUserApiHttpClient;

    @BeforeEach
    public void init(){
        chargeService = new ChargeService(chargeUserApiHttpClient);
        ticketItemList = new ArrayList<>();
        ticketItemList.add(new TicketItem("Leo",new BigDecimal(10),new BigDecimal(3)));
        ticketItemList.add(new TicketItem("Ghilli",new BigDecimal(6),new BigDecimal(1)));
        ticketItemList.add(new TicketItem("Kuruvi",new BigDecimal(8),new BigDecimal(2)));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1",
            "2,2,2",
            "3,3,3"
    })
    void chargeUser(String userId, BigDecimal tip, BigDecimal discount) {
        boolean result = chargeService.chargeUser(userId, ticketItemList, tip, discount);
        verify(chargeUserApiHttpClient).charge(anyString(),any(BigDecimal.class));
    }
}