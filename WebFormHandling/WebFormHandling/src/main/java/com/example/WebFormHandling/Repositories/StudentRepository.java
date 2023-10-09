package com.example.WebFormHandling.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.WebFormHandling.Models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
