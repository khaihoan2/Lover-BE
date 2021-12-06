package com.example.loverbe.model.dto;

import com.example.loverbe.model.entity.ReservationDetail;
import com.example.loverbe.model.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ReservationForm {
    private Long id;

    private User renter;

    private User rentee;

    private String startFrom;

    private String endAt;

    private String location;

    private String reserveAt;

    private Boolean status;

    private Double totalMoney;

    private List<ReservationDetail> reservationDetails;
}
