package com.htec.domain_starter.service.util.impl;

import com.htec.domain_starter.service.util.CharArrayShredder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Nikola Stanar
 * <p>
 * Shreds char array value.
 * @see CharArrayShredder
 */
@Component
@NoArgsConstructor
public class CharArrayShredderImpl implements CharArrayShredder {

    /**
     * Shreds char array value,
     *
     * @param value Value.
     * @see CharArrayShredder#shred(char[])
     */
    @Override
    public void shred(final char[] value) {
        Arrays.fill(value, '\u0000');
    }
}
