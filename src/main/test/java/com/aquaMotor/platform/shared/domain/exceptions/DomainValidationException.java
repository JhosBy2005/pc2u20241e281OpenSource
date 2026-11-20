package com.aquamonitor.platform.u20241e281.shared.domain.exceptions;

/**
 * Exception thrown when a domain object receives invalid data.
 *
 * <p>It is an {@link IllegalArgumentException} with an explanatory English message, and it also
 * carries an i18n message key and its arguments, so the error can be shown in the language
 * requested by the client.</p>
 */
public class DomainValidationException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    private final String messageKey;
    private final transient Object[] arguments;

    /**
     * Creates the exception.
     *
     * @param messageKey     the i18n message key
     * @param defaultMessage the explanatory English message
     * @param arguments      the arguments used to format the message
     */
    public DomainValidationException(String messageKey, String defaultMessage, Object... arguments) {
        super(defaultMessage);
        this.messageKey = messageKey;
        this.arguments = arguments == null ? new Object[0] : arguments.clone();
    }

    /**
     * Gets the i18n message key.
     *
     * @return the message key
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Gets the arguments used to format the message.
     *
     * @return a copy of the message arguments
     */
    public Object[] getArguments() {
        return arguments.clone();
    }
}