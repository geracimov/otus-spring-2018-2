package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
@Transactional
public class CrudRepositoryImpl<T> implements CrudRepository<T> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(T t) {
        em.persist(t);
    }

    @Override
    public T read(Class<T> clazz, UUID uuid) {
        return em.find(clazz, uuid);
    }

    @Override
    public void update(T t) {
        em.merge(t);
    }

    @Override
    public void delete(T t) {
        if (em.contains(t)) {
            em.remove(t);
        } else {
            em.remove(em.merge(t));
        }
    }
}
