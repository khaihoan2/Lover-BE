package com.example.loverbe.service.reservationDetail;

import com.example.loverbe.model.entity.Reservation;
import com.example.loverbe.model.entity.ReservationDetail;
import com.example.loverbe.service.IGeneralService;

public interface IReservationDetailService extends IGeneralService<ReservationDetail> {

    Iterable<ReservationDetail> findByReservation(Reservation reservation);

}
