package testdb.demo.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testdb.demo.entities.Teacher;
import testdb.demo.repositories.TeacherRepositories;

import java.util.List;
import java.util.Map;

@Service
public class DataService {
    @Autowired
    private TeacherRepositories teacherRepository;

    public String getTeachersNamesAndSubjects() {
        JSONObject jsonResponse = new JSONObject();

        try {
            List<Teacher> teachers = teacherRepository.findSalesGreaterThanDate();

            JSONArray labelsArray = new JSONArray();
            JSONArray dataArray = new JSONArray();
            for (Teacher teacher : teachers) {
                labelsArray.put(teacher.getName());
                dataArray.put(teacher.getSubject());
            }

            JSONObject dataset = new JSONObject();
            dataset.put("data", dataArray);

            JSONArray datasetsArray = new JSONArray();
            datasetsArray.put(dataset);

            JSONObject teacherObject= new JSONObject();

            teacherObject.put("labels", labelsArray);
            teacherObject.put("datasets", datasetsArray);

            jsonResponse.put("teachers", teacherObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse.toString();
    }
}
