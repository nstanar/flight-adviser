package com.htec.domain_starter.service.util.impl;

import com.htec.domain_starter.service.util.PasswordShredder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nikola Stanar
 * <p>
 * Shreds password value.
 * @see PasswordShredder
 */
@Component
@NoArgsConstructor
public class PasswordShredderImpl implements PasswordShredder {

    /**
     * Shreds password value,
     *
     * @param value Value.
     * @see PasswordShredder#shred(char[])
     */
    @Override
    public void shred(final char[] value) {
        Arrays.fill(value, '\u0000');
    }
}
