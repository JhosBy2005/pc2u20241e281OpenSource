package com.aquamonitor.platform.u20241e281.shared.application.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents an error that occurred in the application layer.
 *
 * <p>It carries a message key, so the interfaces layer can render a localized message
 * according to the {@code Accept-Language} header, and a default English message used
 * when no translation is available.</p>
 *
 * @param type           the error category
 * @param messageKey     the i18n message key
 * @param defaultMessage the default (English) message
 * @param arguments      the arguments used to format the message
 */
public record ApplicationError(
        ApplicationErrorType type,
        String messageKey,
        String defaultMessage,
        List<Object> arguments) {

    /**
     * Creates an application error, defensively copying the message arguments.
     */
    public ApplicationError {
        arguments = arguments == null
                ? List.of()
                : Collections.unmodifiableList(new ArrayList<>(arguments));
    }

    /**
     * Validation error: input data is invalid or violates constraints.
     *
     * @param messageKey     the i18n message key
     * @param defaultMessage the default (English) message
     * @param arguments      the message arguments
     * @return the application error
     */
    public static ApplicationError validationError(String messageKey, String defaultMessage, Object... arguments) {
        return new ApplicationError(ApplicationErrorType.VALIDATION, messageKey, defaultMessage, asList(arguments));
    }

    /**
     * Not found error: the requested resource does not exist.
     *
     * @param messageKey     the i18n message key
     * @param defaultMessage the default (English) message
     * @param arguments      the message arguments
     * @return the application error
     */
    public static ApplicationError notFound(String messageKey, String defaultMessage, Object... arguments) {
        return new ApplicationError(ApplicationErrorType.NOT_FOUND, messageKey, defaultMessage, asList(arguments));
    }

    /**
     * Conflict error: the operation cannot be completed due to a conflicting state.
     *
     * @param messageKey     the i18n message key
     * @param defaultMessage the default (English) message
     * @param arguments      the message arguments
     * @return the application error
     */
    public static ApplicationError conflict(String messageKey, String defaultMessage, Object... arguments) {
        return new ApplicationError(ApplicationErrorType.CONFLICT, messageKey, defaultMessage, asList(arguments));
    }

    /**
     * Business rule violation error: the operation violates domain constraints.
     *
     * @param messageKey     the i18n message key
     * @param defaultMessage the default (English) message
     * @param arguments      the message arguments
     * @return the application error
     */
    public static ApplicationError businessRuleViolation(String messageKey, String defaultMessage, Object... arguments) {
        return new ApplicationError(ApplicationErrorType.BUSINESS_RULE_VIOLATION, messageKey, defaultMessage,
                asList(arguments));
    }

    /**
     * Unexpected error: something went wrong that should not have.
     *
     * @return the application error with a generic message
     */
    public static ApplicationError unexpected() {
        return new ApplicationError(ApplicationErrorType.UNEXPECTED, "error.unexpected",
                "An unexpected error occurred. Please try again later.", List.of());
    }

    private static List<Object> asList(Object... arguments) {
        return arguments == null ? List.of() : Arrays.asList(arguments);
    }
}