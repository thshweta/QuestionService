package com.example.question_Service.Service;


import com.example.question_Service.DAO.QuestionDao;
import com.example.question_Service.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST); //returning empty array if something goes wrong
    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<String> addQuestion(Question question) {
         questionDao.save(question);
        return new ResponseEntity<>("success" , HttpStatus.CREATED);
    }


    public String deleteById(int id) {
       questionDao.deleteById(id);
        return "Deleted Successfully";
    }


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numQ) {
       List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
       return new ResponseEntity<>(questions,HttpStatus.OK);
    }



}
