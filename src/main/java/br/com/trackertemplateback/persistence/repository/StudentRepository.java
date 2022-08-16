package br.com.trackertemplateback.persistence.repository;

import br.com.trackertemplateback.persistence.model.Student;
import br.com.trackertemplateback.persistence.repository.generic.CustomPagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Cedric Christian on 30/04/2021
 */
public interface StudentRepository extends CustomPagingAndSortingRepository<Student, Long> {

    List<Student> findByNameIgnoreCaseContaining(String name);

    @Query("from Student s where lower(s.name) like lower(concat('%', ?1, '%'))")
    Page<Student> findByName(Pageable pageable, String name);
}
