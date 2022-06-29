package com.msg.after_school.domain.auth.service;

import com.msg.after_school.domain.auth.data.dto.TokenDto;

public interface RedirectService {
    TokenDto execute(String code);
}
