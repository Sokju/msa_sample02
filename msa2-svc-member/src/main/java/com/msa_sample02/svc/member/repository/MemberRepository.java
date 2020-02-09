package com.msa_sample02.svc.member.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msa_sample02.svc.member.domain.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {

	Member findByName(String memeberName);

}
