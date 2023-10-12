package com.koa.commonmodule.testisolation;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
class DatabaseManager {

    private final EntityManager entityManager;
    private final List<String> tableNames;

    public DatabaseManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.tableNames = extractTableNames(entityManager);
    }

    private List<String> extractTableNames(EntityManager entityManager) {
        return entityManager.getMetamodel().getEntities().stream()
                .filter(this::isEntity)
                .map(this::convertCamelToSnake)
                .collect(Collectors.toList());
    }

    private boolean isEntity(EntityType<?> entityType) {
        return entityType.getJavaType().getAnnotation(Entity.class) != null;
    }

    private String convertCamelToSnake(EntityType<?> entityType) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return entityType.getName()
                .replaceAll(regex, replacement)
                .toUpperCase();
    }

    @Transactional
    public void unRestrictForeignKeys() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
    }

    @Transactional
    public void restrictForeignKeys() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    @Transactional
    public void truncateTables() {
        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
    }
}
