package com.ge.healthcare.dose.services.ruleengine;

import com.google.gson.JsonObject;

/**
 * This interface depicts the component main function to write out resulting data.
 */
public interface OutputDataWriter {

    Object writeData(JsonObject object);

    Object getData();

    Object getData(Object idx);

}
