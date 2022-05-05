package com.faredo0o.securitydemo.security;

public enum UserPermissions {
   STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");
   private final String Permission;

    UserPermissions(String permission) {
        Permission = permission;
    }

    public String getPermission() {
        return Permission;
    }
}
