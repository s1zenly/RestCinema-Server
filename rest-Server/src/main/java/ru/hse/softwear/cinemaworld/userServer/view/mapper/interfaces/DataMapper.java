package ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces;


public interface DataMapper<E, M> {
    E toEntity(M model);
    M toModel(E entity);
}
