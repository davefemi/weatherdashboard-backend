package nl.davefemi.weatherdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import nl.davefemi.weatherdashboard.dto.CorrectAnswersDto;
import nl.davefemi.weatherdashboard.dto.QuestionsDto;

@Service
public class TriviaService {
    JsonParser parser;
    UriBuilder urlbuilder;
    RestTemplate restTemplate;

    @Autowired
    public TriviaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApiResponse getQuizQuestions() {
        String url = "https://opentdb.com/api.php?amount=10";
        return restTemplate.getForObject(url, ApiResponse.class);
    }

    public QuestionsDto getQuestions(){
       return new QuestionsDto();
    }

    public CorrectAnswersDto getCorrectAnswers(){
        return new CorrectAnswersDto();
    }

}
