package com.example.Study.System.dao;

import com.example.Study.System.model.GroupCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCourseRepository extends JpaRepository<GroupCourse, Long> {
}
