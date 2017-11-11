package com.compound.request.json.error;

import com.google.api.client.util.Key;

public class JsonErrorMessage {
  @Key
  String message;

  public JsonErrorMessage(String message) {
    this.message = message;
  }
}
