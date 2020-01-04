package com.htec.domain_starter.service.util;

/**
 * @author Nikola Stanar
 * <p>
 * Shreds password value.
 */
@FunctionalInterface
public interface PasswordShredder {

    /**
     * Shreds password value.
     *
     * @param value Value.
     */
    void shred(final char[] value);

}
