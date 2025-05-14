package com.jobcho.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobcho.workspace.Workspaces;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public List<Workspaces> findWorkspacesByUserUserId(Integer userId) {
        return memberRepository.findWorkspacesByUserUserId(userId);
    }
}
