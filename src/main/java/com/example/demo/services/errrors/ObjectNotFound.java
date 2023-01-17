package com.example.demo.services.errrors;

public class ObjectNotFound extends RuntimeException {
  public ObjectNotFound(String err) {
    super(err, null, true, false);
  }
}
