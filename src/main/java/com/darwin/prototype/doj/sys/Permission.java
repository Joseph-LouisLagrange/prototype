package com.darwin.prototype.doj.sys;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 权限实体
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,updatable = false,columnDefinition = "BIGINT UNSIGNED")
    private long ID;

    @NonNull
    @Embedded
    private PermissionExpression permissionExpression;

    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Role.class,mappedBy = "permissions")
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Permission that = (Permission) o;
        return Objects.equals(ID, that.ID);
    }


    @Override
    public int hashCode() {
        return Objects.hash(ID, permissionExpression);
    }
}
