package com.msau.opportunitymanagement.Exceptions;

import java.time.LocalTime;

public class NoRecordFound extends Exception{
    @Override
    public String getMessage() {
        return "NoRecordFound @";
    }
}
