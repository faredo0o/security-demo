package com.faredo0o.securitydemo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.faredo0o.securitydemo.security.UserPermissions.*;

public enum UserRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }
}
