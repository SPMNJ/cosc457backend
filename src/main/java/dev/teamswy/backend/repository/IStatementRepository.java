package dev.teamswy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.teamswy.backend.entity.Chapter;
import dev.teamswy.backend.entity.Statement;

public interface IStatementRepository extends CrudRepository<Statement, Integer> {
    
    @Query(value = "FROM Statement s WHERE s.chapter = ?1", nativeQuery = false)
    Iterable<Statement> findByChapter(Chapter chapter);
    
}
