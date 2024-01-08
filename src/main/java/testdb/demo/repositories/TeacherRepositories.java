package testdb.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import testdb.demo.entities.Teacher;

import java.util.List;

public interface TeacherRepositories extends JpaRepository<Teacher, Long> {
    @Query("SELECT new Teacher(t.name, t.subject) FROM Teacher t")
    List<Teacher> findSalesGreaterThanDate();
}
