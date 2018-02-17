/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author script-dark
 */
public interface GenericService<T, ID> {
    void save(T entity);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
}
