package ru.geracimov.otus.spring.hw25springintegration.config;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

public class MyPermission extends BasePermission {
    public static final Permission ALL = new MyPermission(31, 'X');
    public static final Permission RW = new MyPermission(2, 'E');

    protected MyPermission(int mask, char code) {
        super(mask, code);
    }

    protected MyPermission(int mask) {
        super(mask);
    }

    @Override
    public String getPattern() {
        return super.getPattern();
    }
}
