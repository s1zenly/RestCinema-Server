package ru.hse.softwear.cinemaworld.restServer.view.mapper.interfaces;

public interface DataMapperWithoutDependency<E, M> {
    E toEntityWithoutDependency(M model);
    M toModelWithoutDependency(E entity);
}
