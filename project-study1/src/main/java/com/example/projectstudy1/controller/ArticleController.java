package com.example.projectstudy1.controller;

import com.example.projectstudy1.dto.ArticleForm;
import com.example.projectstudy1.entity.Article;
import com.example.projectstudy1.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 연결해줌, 자동으로
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleForm articleForm) {
        log.info(articleForm.toString());
        // 1. Dto -> Entity 변환
        Article article = articleForm.toEntity();
        log.info(article.toString());
        // 2. Repository에게 Entity 데이터를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }
}
