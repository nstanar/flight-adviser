package com.htec.domain_starter.service.util;

/**
 * @author Nikola Stanar
 * <p>
 * Shreds char array value.
 */
@FunctionalInterface
public interface CharArrayShredder {

    /**
     * Shreds char array value.
     *
     * @param value Value.
     */
    void shred(final char[] value);

}
