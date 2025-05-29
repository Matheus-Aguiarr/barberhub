package com.barberhub.BarberHub.repository;

import com.barberhub.BarberHub.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    List<AppointmentModel> findByUserId(Long userId);



    //Isso eh uma query JPQL, que eh como se fosse um SQL, mas funciona com os nomes dos atributos das entidades java, nao dos nomes das tabelas diretamente como o SQL comum
//    "Me traz (SELECT) todos os registros (a) da tabela AppointmentModel". (a -> apelido dado ao appointmentmodel para facilitar a escrita)
//    WHERE a.service.id = :serviceId -> Filtra apenas os appointments cujo ID do servico eh igual ao que foi passado na variavel serviceID.
//    AND DATE(a.dateTime) = :date -> a.dateTime eh um LocalDateTime, ouseja, data e hora juntas.
//    DATE(a.dateTime) faz uma conversao -> ele tira a parte da hora e deixa so a data
//    Assim, compara apenas a data ignorando o horario.
    @Query("SELECT a FROM AppointmentModel a WHERE a.service.id = :serviceId AND DATE(a.dateTime) = :date")
//    @Param("serviceId") Long serviceId, @Param("date") LocalDate date -> serve para passar os parametros :serviceId e :date para dentro da query
    List<AppointmentModel> findByServiceIdAndDate(@Param("serviceId") Long serviceId, @Param("date") LocalDate date);

//    Resumo da query -> Me retorna todos os agendamentos (AppointmentModel) que sao do servico tal (serviceId) e que estao na data tal (date), independente do horario
}
