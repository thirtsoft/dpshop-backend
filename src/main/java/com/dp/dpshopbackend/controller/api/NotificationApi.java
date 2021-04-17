package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.NotificationDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface NotificationApi {

    @PostMapping(value = APP_ROOT + "/notifications/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto);

    @GetMapping(value = APP_ROOT + "/notifications/{idNotification}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NotificationDto> findById(@PathVariable("idNotification") Long id);

    @GetMapping(value = APP_ROOT + "/notifications/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NotificationDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/notifications/delete/{idNotification}")
    void delete(@PathVariable("idNotification") Long id);
}
