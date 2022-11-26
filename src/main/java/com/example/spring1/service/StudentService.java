package com.example.spring1.service;

import com.example.spring1.model.Student;

import java.util.List;

public interface StudentService {
    /**
     * Создает нового ученика
     * @param student - клиент для создания
     */
    void create(Student student);

    /**
     * Возвращает список всех учеников
     * @return список учеников
     */
    List<Student> readAll();


    /**
     * Возвращает всех учеников из класса
     * @param group_name - название класса
     * @return - список учеников
     */
    List<Student> readGroup(String group_name);

    /**
     * Возвращает ученика по его ID
     * @param id - ID ученика
     * @return - объект ученика с заданным ID
     */

    Student read_by_id(int id);

    /**
     * Обновляет ученик с заданным ID,
     * в соответствии с переданным клиентом
     * @param student - данные ученика в соответсвии с которым нужно обновить данные
     * @param id - id ученика которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Student student, int id);

    /**
     * Удаляет ученика с заданным ID
     * @param id - id ученика, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
    Student read2(String id_student);
}
