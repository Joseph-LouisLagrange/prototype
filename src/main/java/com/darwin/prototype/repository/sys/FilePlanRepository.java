package com.darwin.prototype.repository.sys;


import com.darwin.prototype.po.sys.FilePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilePlanRepository extends JpaRepository<FilePlan,Long> {
}
