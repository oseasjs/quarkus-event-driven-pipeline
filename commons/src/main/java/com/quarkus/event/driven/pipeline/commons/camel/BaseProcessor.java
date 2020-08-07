package com.quarkus.event.driven.pipeline.commons.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class BaseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        if (cause != null) {
            cause.printStackTrace();
            exchange.getMessage().setHeader("stage", getModuleName());
            exchange.getMessage().setHeader("error", cause.getClass().getName());
            exchange.getMessage().setHeader("cause", cause.getMessage());
        }

        processEvent(exchange);

    }

    protected abstract void processEvent(Exchange exchange) throws Exception;

    protected abstract String getModuleName();

}
