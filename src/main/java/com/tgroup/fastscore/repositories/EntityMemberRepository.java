package com.tgroup.fastscore.repositories;

import com.tgroup.fastscore.entities.EntityMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntityMemberRepository  extends CrudRepository<EntityMember, UUID> {
}
