package dev.teamswy.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// CRUD refers Create, Read, Update, Delete

import dev.teamswy.backend.entity.Chapter;

public interface IChapterRepository extends CrudRepository<Chapter, Integer> {

        //Find by chapter name
        Optional<Chapter> findByChapterName(String chapterName);
}