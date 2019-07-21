package br.com.juno.recruta.backend.repository;

import br.com.juno.recruta.backend.entity.BaseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, E> extends Repository<T, E> {

    Optional<T> findById(E e);

    Optional<List<T>> findAll();

    @Query(value =
            " SELECT                                                                                                 " +
            "       entity.*                                                                                         " +
            " FROM                                                                                                   " +
            "       #{#entityName} AS entity                                                                         " +
            " INNER JOIN (                                                                                           " +
            "                 SELECT                                                                                 " +
            "                   id,                                                                                  " +
            "                   LENGTH(UPPER(name)) - LENGTH(REPLACE(UPPER(name), UPPER(:pattern),'')) AS occurrence " +
            "                 FROM                                                                                   " +
            "                   #{#entityName}                                                                       " +
            "             ) AS innerEntity ON (entity.id = innerEntity.id)                                           " +
            " WHERE                                                                                                  " +
            "       innerEntity.occurrence > 0                                                                       " +
            " ORDER BY                                                                                               " +
            "       innerEntity.occurrence                                                                           " +
            " DESC   " +
            " LIMIT 1", nativeQuery = true)
    Optional<T> findWithPattern(String pattern);
}
