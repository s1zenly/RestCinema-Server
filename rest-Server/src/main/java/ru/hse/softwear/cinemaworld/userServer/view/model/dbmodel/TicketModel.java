package ru.hse.softwear.cinemaworld.userServer.view.model.dbmodel;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {
    private Long id;
    private Integer section;
    private Integer subsection;
}
