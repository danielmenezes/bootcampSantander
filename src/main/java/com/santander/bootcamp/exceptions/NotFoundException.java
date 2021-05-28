package com.santander.bootcamp.exceptions;

import com.santander.bootcamp.util.MessageUtils;

public class NotFoundException extends RuntimeException {
    public NotFoundException() { super(MessageUtils.STOCK_NOT_EXITS); }
}
