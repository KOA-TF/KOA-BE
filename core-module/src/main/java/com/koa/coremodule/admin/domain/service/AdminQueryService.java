package com.koa.coremodule.admin.domain.service;

import com.koa.coremodule.admin.domain.entity.Admin;
import com.koa.coremodule.admin.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminQueryService {

    private final AdminRepository adminRepository;

    public Long checkPassword(Integer password) {

        Admin admin = adminRepository.findAdminByPassword(password);
        return admin.getId();
    }
}
