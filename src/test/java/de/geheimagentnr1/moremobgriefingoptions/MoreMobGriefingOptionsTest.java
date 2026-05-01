package de.geheimagentnr1.moremobgriefingoptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MoreMobGriefingOptionsTest {

    @Test
    void modIdIsValid() {

        String modId = "moremobgriefingoptions";
        assertTrue( modId.matches( "[a-z][a-z0-9_]{1,63}" ) );
    }
}
