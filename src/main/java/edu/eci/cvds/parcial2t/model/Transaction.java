package edu.eci.cvds.parcial2t.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "Transactions")
public class Transaction {
    private String transactionId;
    private double totalAmount;
    private Status status;
    private String responseMessage;
}
