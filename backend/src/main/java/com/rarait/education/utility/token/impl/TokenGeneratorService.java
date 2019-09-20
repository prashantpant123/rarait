package com.rarait.education.utility.token.impl;

import com.rarait.education.utility.token.spi.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class TokenGeneratorService implements TokenProvider {

    @Override
    public String getAlphaNumeric(String tokenLength){
        return TokenGenerator.getAlphaNumeric(Integer.valueOf(tokenLength));
    }

    @Override
    public String getNumeric(){
        return TokenGenerator.getNumeric();
    }
}