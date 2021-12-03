package dev.teamswy.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dev.teamswy.backend.entity.Chapter;
import dev.teamswy.backend.entity.Prospective_Member;

public interface IProspectRepository extends CrudRepository<Prospective_Member, Integer> {

    @Query(value = "Select p from Prospective_Member p where p.bidchapter = :chapter")
    List<Prospective_Member> findByChapter(@Param("chapter") Chapter chapter);

    @Query(value = "Select p from Prospective_Member p where p.phone = :phone")
    Optional<Prospective_Member> findById(@Param("phone") String phone);
}
