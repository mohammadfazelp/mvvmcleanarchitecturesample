package com.mvvmcleanarchitecturesample.data.exception;

/**
 * Exception throw by the application when a Feed search can't return a valid result.
 */
public class FeedNotFoundException extends Exception {
  public FeedNotFoundException() {
    super();
  }
}
