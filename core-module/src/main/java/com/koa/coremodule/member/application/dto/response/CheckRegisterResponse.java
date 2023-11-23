package com.koa.coremodule.member.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckRegisterResponse {
    private ReisterResult checkRegistered;

    public enum ReisterResult {
        NO_ACCOUNT,
        REGISTERED,
        CAN_REGISTER
    }

    public CheckRegisterResponse(Boolean isMember, Boolean isRegistered) {
        if(isMember && isRegistered) {
            checkRegistered = ReisterResult.REGISTERED;
        } else if(isMember && !isRegistered) {
            checkRegistered = ReisterResult.CAN_REGISTER;
        } else {
            checkRegistered = ReisterResult.NO_ACCOUNT;
        }
    }
}
