/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pokemon;
import entities.Trainer;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author usu26
 */
@Stateless
public class StukemonEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public boolean trainerExist(String name) {
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        Trainer c = em.find(Trainer.class, name);
        em.close();
        return c != null;
    }
    //if needed PROVISIONAL
    public Trainer getTrainer(String name) {
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        Trainer c = em.find(Trainer.class, name);
         em.close();
        if(c !=null){
            return c;
        }
       return null;
    }

    public boolean createTrainer(Trainer c) {
        EntityManager em = emf.createEntityManager();
        if (!trainerExist(c.getName())) {
            em.persist(c);
            em.flush();
            em.close();
            return true;
        }
        return false;
    }
    public boolean deleteTrainer(String nombre) {
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        Trainer trainer = em.find(Trainer.class, nombre);
        if(trainer != null){
            em.remove(trainer);
            em.close();
            return true;
        }
        return false;
    }
    public boolean createPoke(Pokemon c) {
        EntityManager em = emf.createEntityManager();
            if (!pokeExist(c.getName())) {
            em.persist(c);
            em.flush();
            em.close();
            return true;
            }
        return false;
    }
     public boolean pokeExist(String name) {
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        Pokemon c = em.find(Pokemon.class, name);
        em.close();
        return c != null;
    }
    //if needed PROVISIONAL
    public Pokemon getPoke(String name) {
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        Pokemon c = em.find(Pokemon.class, name);
         em.close();
        if(c !=null){
            return c;
        }
       return null;
    }
    public boolean deletePoke(String nombre) {
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        Pokemon poke = em.find(Pokemon.class, nombre);
        if(poke != null){
            em.remove(poke);
            em.close();
            return true;
        }
        return false;
    }
    public boolean countPoke(Trainer trainer) {
         List<Pokemon> res = emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", trainer).getResultList();
        return (res.size() <= 5);
    }
    public List<Pokemon> selectAllPokemon() {
        return emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
}
    public List<Pokemon> getPokemonsByTrainer(String name) {
        Trainer trainer = getTrainer(name);
        return emf.createEntityManager().createNamedQuery("Pokemon.findByTrainer").setParameter("trainer", trainer).getResultList();

    }
}
