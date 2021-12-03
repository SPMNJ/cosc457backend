package dev.teamswy.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.teamswy.backend.entity.Chapter;
import dev.teamswy.backend.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, Integer> {

    @Query(value = "FROM Role r WHERE r.member.chapter = ?1", nativeQuery = false)
    Iterable<Role> findByChapter(Chapter chapter);

}
