package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.services.EmailService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(String from, String to) {

    }
}
