package com.wellsfargo.counselor.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "advisors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long advisor_id;


    private String first_name;


    private String last_name;


    private String address;


    private String phone;


    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advisor")
    private List<Client> clients;


    }


