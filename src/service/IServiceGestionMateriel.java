/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author walid
 */
public interface IServiceGestionMateriel <T> {
     public void ajouter(T t );
    public void modifier(T t);
    public List<T> afficher();
    public void supprimer(T t);
}
