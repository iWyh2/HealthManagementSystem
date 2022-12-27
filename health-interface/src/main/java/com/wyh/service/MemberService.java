package com.wyh.service;

import com.wyh.pojo.Member;

import java.util.List;

public interface MemberService {
    void add(Member member);
    Member findByTelephone(String telephone);
    List<Integer> findMemberCountByMonth(List<String> month);
}
