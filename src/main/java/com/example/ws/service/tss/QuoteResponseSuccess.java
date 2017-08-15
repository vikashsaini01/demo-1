package com.example.ws.service.tss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuoteResponseSuccess {

    /**
     * The total number of objects contained in the response.
     */
    private int total;

    /**
     * Contruct a new QuoteResponseSuccess object.
     */
    public QuoteResponseSuccess() {

    }

    /**
     * Returns the value of the total attribute.
     * @return An int value.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total attribute.
     * @param total An int value.
     */
    public void setTotal(int total) {
        this.total = total;
    }

}