package com.tahrioussama.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping("/notification")
    void sendNotification(NotificationRequest notificationRequest);
}
