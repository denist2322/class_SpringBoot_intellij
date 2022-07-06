package com.mysite.sbb.controller;

import com.mysite.sbb.dao.ArticleRepository;
import com.mysite.sbb.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/test")
    @ResponseBody
    public String testFunc() {
        return "test";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }


    @RequestMapping("/detail1")
    @ResponseBody
    public Article showDetail(int id) {
        List<Article> articles = articleRepository.findAll();
        Article article = articles.get(id);
        return article;
    }

//    @RequestMapping("/detail")
//    @ResponseBody
//    public Article showDetail() {
//        return articleRepository.findById(1L).get();
//    }
//
    @RequestMapping("/detail2")
    @ResponseBody
    public Article showDetail2(@RequestParam Long id) {
        return articleRepository.findById(id).get();
    }

    @RequestMapping("/detail3")
    @ResponseBody
    public Article showDetail3(@RequestParam Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public String articleModify(@RequestParam Long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if(article.getTitle() == null){
            return "제목 없다.";
        }

        if(article.getBody() == null){
            return "내용 없다.";
        }

        article.setTitle(title);
        article.setBody(body);
        articleRepository.save(article);

        return "수정 됐다.";

    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String articleModify(@RequestParam Long id) {
        Article article = articleRepository.findById(id).get();
        
        articleRepository.delete(article);

        return "없어짐.";
    }

}
