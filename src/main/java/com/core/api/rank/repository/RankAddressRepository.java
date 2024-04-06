package com.core.api.rank.repository;

import com.core.api.rank.entity.RankAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankAddressRepository extends JpaRepository<RankAddress, Long>{
}
