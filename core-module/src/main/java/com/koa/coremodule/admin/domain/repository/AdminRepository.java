package com.koa.coremodule.admin.domain.repository;

import com.koa.coremodule.admin.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByPassword(Integer password);

}
