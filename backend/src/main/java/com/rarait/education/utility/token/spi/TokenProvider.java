package com.rarait.education.utility.token.spi;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface TokenProvider {

    String getAlphaNumeric(String tokenLength);

    String getNumeric();
}
