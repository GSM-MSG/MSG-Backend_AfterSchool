package com.msg.after_school.domain.auth.service;

import com.msg.after_school.domain.auth.data.dto.TokenDto;

public interface RefreshService {
    TokenDto execute(String refresh);
}
