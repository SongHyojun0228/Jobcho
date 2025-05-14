package com.jobcho.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobcho.workspace.Workspaces;

public interface MemberRepository extends JpaRepository<Members, Integer>{
	@Query("SELECT m.workspace FROM Members m WHERE m.user.userId = :userId")
	List<Workspaces> findWorkspacesByUserUserId(@Param("userId") Integer userId);

}
