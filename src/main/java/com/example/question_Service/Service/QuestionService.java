package com.example.question_Service.Service;


import com.example.question_Service.DAO.QuestionDao;
import com.example.question_Service.Model.Question;
import com.example.question_Service.Model.QuestionWrapper;
import com.example.question_Service.Model.Response;
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


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(int id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper wrapper1 = new QuestionWrapper();
            wrapper1.setId(question.getId());
            wrapper1.setQuestionTitle(question.getQuestionTitle());
            wrapper1.setOption1(question.getOption1());
            wrapper1.setOption2(question.getOption2());
            wrapper1.setOption3(question.getOption3());
            wrapper1.setOption4(question.getOption4());
            wrapper.add(wrapper1);
        }

        return new ResponseEntity<>(wrapper , HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;

        for(Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right , HttpStatus.OK);
    }
}
