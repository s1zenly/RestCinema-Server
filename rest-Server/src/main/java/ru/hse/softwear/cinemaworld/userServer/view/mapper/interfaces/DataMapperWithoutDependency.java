package ru.hse.softwear.cinemaworld.userServer.view.mapper.interfaces;

public interface DataMapperWithoutDependency<E, M> {
    E toEntityWithoutDependency(M model);
    M toModelWithoutDependency(E entity);
}
