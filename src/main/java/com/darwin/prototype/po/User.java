package com.darwin.prototype.po;

import com.darwin.prototype.po.sys.Role;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false,updatable = false,insertable = false,columnDefinition = "bigint UNSIGNED")
    private long ID;

    @NonNull
    @Column(name = "name",columnDefinition = "VARCHAR(128)")
    private String name;

    @NonNull
    @Column(name = "age",columnDefinition = "INT")
    private Integer age;

    @NonNull
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Role> role = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(ID, user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, age);
    }
}
