package testdb.demo.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testdb.demo.entities.Teacher;
import testdb.demo.repositories.TeacherRepositories;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private TeacherRepositories teacherRepository;

    public String getTeachersNamesAndSubjects() {
        JSONObject jsonResponse = new JSONObject();

        try {
            List<Teacher> teachers = teacherRepository.findSalesGreaterThanDate();

            // Montagem da resposta JSON com os dados dos professores
            JSONArray teachersArray = new JSONArray();
            for (Teacher teacher : teachers) {
                JSONObject teacherJson = new JSONObject();
                teacherJson.put("name", teacher.getName());
                teacherJson.put("subject", teacher.getSubject());
                teachersArray.put(teacherJson);
            }

            jsonResponse.put("teachers", teachersArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse.toString();
    }
}
