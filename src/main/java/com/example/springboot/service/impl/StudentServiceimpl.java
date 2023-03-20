package com.example.springboot.service.impl;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Student;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repository.StudentRepository;
import com.example.springboot.service.StudentService;

@Service
public class StudentServiceimpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository; 
	
	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
//		Optional<Student> student = studentRepository.findById(id);
//		if(student.isPresent())
//			return student.get();
//		else
//		{
//			throw new ResourceNotFoundException("Student", "Id", id);
//		}
		
		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
	}

	@Override
	public Student updateStudent(Student student, long id) {
		Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPhnNumber(student.getPhnNumber());
		
		studentRepository.save(existingStudent);
		
		return existingStudent;
	}

	@Override
	public void deleteStudent(long id) {
		studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		
		studentRepository.deleteById(id);
	}
	
}
