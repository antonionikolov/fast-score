package com.tgroup.fastscore.services;

import com.tgroup.fastscore.repositories.EntityMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityMemberServiceImpl implements EntityMemberService {
    private final EntityMemberRepository entityMemberRepository;
}
