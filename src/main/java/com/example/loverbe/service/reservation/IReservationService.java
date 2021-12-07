package com.example.loverbe.service.reservation;

import com.example.loverbe.model.entity.Reservation;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.service.IGeneralService;


public interface IReservationService extends IGeneralService<Reservation> {

    Iterable<Reservation> findByRenter(User user);

}
