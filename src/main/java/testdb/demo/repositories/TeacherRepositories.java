package testdb.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import testdb.demo.entities.Teacher;

public interface TeacherRepositories extends JpaRepository<Teacher, Long> {
}
