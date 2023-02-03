package io.core.app.models.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "PRODUCT_STAGING")// with progress user is keywordof
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String industry;
    @CsvBindByPosition(position = 2)
    private String sector;
    @CsvBindByPosition(position = 3)
    private String website;
    @CsvBindByPosition(position = 4)
    private String rank;

}
