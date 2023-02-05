package io.core.app.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "PRODUCTS")// with progress user is keywordof
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Industry industry;

    @ManyToOne(fetch = FetchType.EAGER)
    private Sector sector;

    @NotNull(message = "Website cannot be null")
    private String website;

    @NotNull(message = "RANK cannot be null")
    private String rank;

}
