package de.haegerconsulting.hrmanagement.mainartifact.service;


import de.haegerconsulting.hrmanagement.mainartifact.exception.exceptiontypes.ResourceNotFoundException;
import de.haegerconsulting.hrmanagement.mainartifact.model.IdEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

public interface GenericService<T extends IdEntity, ID extends Serializable> {

    public default Iterable<T> findAll(Iterable<ID> ids) throws ResourceNotFoundException {
        return getRepository().findAll(ids);
    }

    public default Iterable<T> findAll() throws ResourceNotFoundException {
        return getRepository().findAll();
    }

    public default T get(ID id) throws ResourceNotFoundException {
        T t = getRepository().findOne(id);

        if (t == null) {
            throw new ResourceNotFoundException(id.toString(), "user not found");
        }
        return t;
    }

    public default T save(T entity) {
        return getRepository().save(entity);
    }

    public default void delete(ID id) throws ResourceNotFoundException {
        if (getRepository().exists(id)) {
            getRepository().delete(id);
        }
        else {
            throw new ResourceNotFoundException(id.toString(), "user not found");
        }
    }

    public default void update(T entity) throws ResourceNotFoundException{
        if (getRepository().exists(getId(entity))) {
            getRepository().save(entity);
        }
        else {
            throw new ResourceNotFoundException(entity.getId(),"Can't update because it doesn't exist in DB: ");
        }
    }

    public ID getId(T entity);

    public CrudRepository<T, ID> getRepository();
}