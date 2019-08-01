package br.com.juno.recruta.backend.service;

import br.com.juno.recruta.backend.entity.BaseEntity;
import br.com.juno.recruta.backend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity> {
    protected final BaseRepository repository;

    public BaseService(BaseRepository repository) {
        this.repository = repository;
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<List<T>> findAll() {
        return repository.findAll();
    }

    public Optional<T> findWithPattern(String pattern) {
        return repository.findWithPattern(pattern);
    }
}
