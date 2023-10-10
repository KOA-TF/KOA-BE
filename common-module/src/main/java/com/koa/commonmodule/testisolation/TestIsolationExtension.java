package com.koa.commonmodule.testisolation;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

class TestIsolationExtension implements AfterEachCallback, BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        DatabaseManager databaseManager = getDatabaseManager(context);
        databaseManager.unRestrictForeignKeys();
    }


    @Override
    public void afterEach(ExtensionContext context) {
        DatabaseManager databaseManager = getDatabaseManager(context);
        databaseManager.truncateTables();
        databaseManager.restrictForeignKeys();
    }

    private DatabaseManager getDatabaseManager(ExtensionContext context) {
        return (DatabaseManager) SpringExtension
                .getApplicationContext(context)
                .getBean("databaseManager");
    }
}
