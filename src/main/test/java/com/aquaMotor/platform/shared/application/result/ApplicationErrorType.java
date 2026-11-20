package com.aquamonitor.platform.u20241e281.shared.application.result;

/**
 * Categories of errors produced by the application layer.
 *
 * <p>The interfaces layer uses this category to choose the HTTP status of an error response.</p>
 */
public enum ApplicationErrorType {
    /** Input data is invalid or violates a domain invariant. */
    VALIDATION,
    /** The requested resource does not exist. */
    NOT_FOUND,
    /** The operation conflicts with the current state, for example a duplicated unique value. */
    CONFLICT,
    /** The operation violates a business rule. */
    BUSINESS_RULE_VIOLATION,
    /** Something went wrong that was not expected. */
    UNEXPECTED
}