package ru.hse.softwear.cinemaworld.admin.service;

public interface CRUDservice<E, I> {

    void create(Object... objects);
    E read(I id);
    void update(I id, E entity);

    void delete(Object... objects);
}
