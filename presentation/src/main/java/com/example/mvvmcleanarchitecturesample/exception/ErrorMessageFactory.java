package com.example.mvvmcleanarchitecturesample.exception;

import android.content.Context;

import com.example.mvvmcleanarchitecturesample.R;
import com.mvvmcleanarchitecturesample.data.exception.NetworkConnectionException;
import com.mvvmcleanarchitecturesample.data.exception.FeedNotFoundException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof FeedNotFoundException) {
            message = context.getString(R.string.exception_message_user_not_found);
        }

        return message;
    }
}

