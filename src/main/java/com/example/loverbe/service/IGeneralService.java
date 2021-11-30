package com.example.loverbe.service;

import java.util.Optional;

public interface IGeneralService<T> {
<<<<<<< HEAD
=======

>>>>>>> 6aeedc4d8fa00df84a1d0d72353c3cae0e4d9ca1
    Iterable<T> findAll();

    T save(T t);

    void delete(Long id);

    Optional<T> findById(Long id);
<<<<<<< HEAD

=======
>>>>>>> 6aeedc4d8fa00df84a1d0d72353c3cae0e4d9ca1
}
