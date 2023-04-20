package com.example.projectstudy1.repository;

import com.example.projectstudy1.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
