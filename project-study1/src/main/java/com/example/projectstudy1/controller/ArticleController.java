package com.example.projectstudy1.controller;

import com.example.projectstudy1.dto.ArticleForm;
import com.example.projectstudy1.entity.Article;
import com.example.projectstudy1.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 연결해줌, 자동으로
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleForm articleForm) {
        // 1. Dto -> Entity 변환
        Article article = articleForm.toEntity();
        // 2. Repository에게 Entity 데이터를 DB에 저장
        Article saved = articleRepository.save(article);

        return "";
    }
}
