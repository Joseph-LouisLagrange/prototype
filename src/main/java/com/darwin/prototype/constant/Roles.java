package com.darwin.prototype.constant;

public enum Roles {
    ADMIN("ADMIN"),USER("USER");
    private final String roleName;
    Roles(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
