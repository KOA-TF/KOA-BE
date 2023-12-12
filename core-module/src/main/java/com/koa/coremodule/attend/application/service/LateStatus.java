package com.koa.coremodule.attend.application.service;

public enum LateStatus {
    PASS("수료 가능한 점수에요."),
    FAIL("수료 불가능한 점수에요.");

    private final String message;

    LateStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String calculatePassYn(int penalty) {

        String passYn;

        if (penalty <= 5) {
            passYn = LateStatus.PASS.getMessage();
        } else {
            passYn = LateStatus.FAIL.getMessage();
        }

        return passYn;
    }
}
