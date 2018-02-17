package repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;


/**
 *
 * @author script-dark
 */
public interface GenericRepository<T> {
   void save(T entity);
   void update(T entity);
    void delete(int id);
    T findById(int id);
    List<T>findAll();
}
