package nl.davefemi.weatherdashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.davefemi.weatherdashboard.dto.CheckAnswerDto;
import nl.davefemi.weatherdashboard.dto.QuestionsDto;
import nl.davefemi.weatherdashboard.service.TriviaService;

@RestController
@RequestMapping("public/trivia")
public class TriviaController {
    private final TriviaService service;

    @Autowired
    public TriviaController(TriviaService service){
        this.service = service;
    }

    @GetMapping("/get-questions")
    public QuestionsDto getQuestions(){
        return service.getQuestions();
    }

    @PostMapping("{responsecode}/check-answer")
    public CheckAnswerDto getCorrectAnswers(@RequestBody String answer ){
        return new CheckAnswerDto();
    }
}
