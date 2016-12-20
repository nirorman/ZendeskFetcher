package com.nirorman.zendesk.exceptions;

import java.io.IOException;

/**
 * Created by ormann on 14/12/2016.
 */
public class ReadResourceException extends IOException{

    public ReadResourceException(String message) {
        super(message);
    }

}
