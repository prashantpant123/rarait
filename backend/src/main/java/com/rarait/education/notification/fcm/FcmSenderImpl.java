package com.rarait.education.notification.fcm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rarait.education.notification.fcm.resource.ErrorDetailResource;
import com.rarait.education.notification.fcm.resource.FcmPushRequest;
import com.rarait.education.notification.fcm.resource.FirebaseResponse;
import com.rarait.education.shared.Profiles;
import com.rarait.framework.exception.ClientRestException;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
@Profile(ProfileNames.PROD)
public class FcmSenderImpl implements FcmSender {

    private final FcmConfigRepository fcmConfigRepository;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper;

    @Lazy
    @Autowired
    public FcmSenderImpl(FcmConfigRepository fcmConfigRepository,
                         @Qualifier("fcmRestTemplate") RestTemplate restTemplate) {
        this.fcmConfigRepository = fcmConfigRepository;
        this.restTemplate = restTemplate;
        this.httpHeaders = new HttpHeaders();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void sendFcm(FcmPushRequest request) {
        try {
            ResponseEntity<Object> responseEntity = restTemplate.exchange("urls", HttpMethod.POST,
                    new HttpEntity<>(request, httpHeaders), Object.class);

            if (!(responseEntity.getStatusCode().equals(HttpStatus.OK) || responseEntity.getStatusCode().equals(HttpStatus.CREATED)
                    || responseEntity.getStatusCode().equals(HttpStatus.ACCEPTED))) {
                ErrorDetailResource errorResponse = objectMapper.convertValue(responseEntity.getBody(),
                        new TypeReference<ErrorDetailResource>() {
                });
                log.error("FCM - Status Code: {} , Error Message: {}", responseEntity.getStatusCode(), errorResponse.getMessage());
                throw new ClientRestException(errorResponse.getErrorMessage());
            }
//            return this.objectMapper.convertValue(responseEntity.getBody(), new TypeReference<FirebaseResponse>() {
//            });
        } catch (IllegalArgumentException | RestClientException exc) {
            log.error("Error: {}", exc);
            throw new ClientRestException("Failed to send FCM notification");
        }
    }
}