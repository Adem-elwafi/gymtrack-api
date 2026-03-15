package com.gymtrack.gymtrack_api.repository;

import com.gymtrack.gymtrack_api.model.Member; // Import mis à jour
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}