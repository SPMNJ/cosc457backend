package dev.teamswy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dev.teamswy.backend.entity.Chapter;
import dev.teamswy.backend.entity.Statement;

public interface IStatementRepository extends CrudRepository<Statement, Integer> {
    
    @Query(value = "SELECT statements.* FROM chapter_statements INNER JOIN statements ON chapter_statements.refid = statements.ref_id WHERE chapter_statements.chapterId = ?1", nativeQuery = true)
    List<Statement> findByChapter(Chapter chapter);
    
}
