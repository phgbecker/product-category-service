package br.com.juno.recruta.backend.controller;

import br.com.juno.recruta.backend.entity.BaseEntity;
import br.com.juno.recruta.backend.service.BaseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class BaseController<T extends BaseEntity> {
    protected final BaseService<T> service;
    private final Predicate<Optional<T>> isPresent = t -> t.isPresent();
    private final Predicate<Optional<List<T>>> isNotEmpty = t -> t.isPresent() && !t.get().isEmpty();

    public BaseController(BaseService<T> service) {
        this.service = service;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> id(@PathVariable Long id) {
        return Optional.of(
                service.findById(id)
        ).filter(
                isPresent
        ).map(
                response -> ResponseEntity.ok(response.get())
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    @GetMapping(value = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> listAll() {
        return Optional.of(
                service.findAll()
        ).filter(
                isNotEmpty
        ).map(
                response -> ResponseEntity.ok(response.get())
        ).orElse(
                ResponseEntity.noContent().build()
        );
    }

    @GetMapping(value = "/withPattern/{pattern}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> withPattern(@PathVariable String pattern) {
        return Optional.of(
                service.findWithPattern(pattern)
        ).filter(
                isPresent
        ).map(
                response -> ResponseEntity.ok(response.get())
        ).orElse(
                ResponseEntity.noContent().build()
        );
    }
}
