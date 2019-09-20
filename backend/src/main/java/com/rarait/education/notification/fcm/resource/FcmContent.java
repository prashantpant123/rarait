package com.rarait.education.notification.fcm.resource;

import com.rarait.framework.shared.InputUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Builder
@ToString
public class FcmContent implements Serializable {

    private String body;
    private boolean update;
    private String title;
    private String externalRedirectUrl;
    private String appOnClick;
    private String image;
    private Map<String, String> properties;

//    public FcmContent(String title, String body, boolean update) {
//        this.body = body;
//        this.title = title;
//        this.update = update;
//    }
//
//    public FcmContent(String title, String body, boolean update,
//                      String externalRedirectUrl, String imagePath,
//                      Map<String, String> properties) {
//        this.body = body;
//        this.title = title;
//        this.update = update;
//        if (!InputUtil.isEmpty(externalRedirectUrl) && !externalRedirectUrl.equals("null")) {
//            this.externalRedirectUrl = externalRedirectUrl;
//        }
//        this.image = imagePath;
//        this.properties = properties;
//        if (!InputUtil.isEmpty(externalRedirectUrl) && !externalRedirectUrl.startsWith("http")
//                && !externalRedirectUrl.equals("null")) {
//            this.appOnClick = externalRedirectUrl;
//        }
//    }
}