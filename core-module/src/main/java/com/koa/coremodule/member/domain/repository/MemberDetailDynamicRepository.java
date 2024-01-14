package com.koa.coremodule.member.domain.repository;

import com.koa.coremodule.member.domain.entity.MemberDetail;
import java.util.List;

public interface MemberDetailDynamicRepository {

    List<MemberDetail> findAllSortedByPartAndName();
}
