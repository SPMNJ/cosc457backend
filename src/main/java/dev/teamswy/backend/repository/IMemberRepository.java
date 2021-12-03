package dev.teamswy.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dev.teamswy.backend.ChapterMember;
import dev.teamswy.backend.entity.Chapter;
import dev.teamswy.backend.entity.Member;

public interface IMemberRepository extends CrudRepository<Member, ChapterMember> {

    @Query(value = "FROM Member m WHERE m.chapter = ?1", nativeQuery = false)
    Iterable<Member> findByChapter(Chapter chapter);
    
    @Query(value = "Select m FROM Member m WHERE m.phone = :phone", nativeQuery = false)
    Optional<Member> findByPhone(@Param("phone") String phone);
}
