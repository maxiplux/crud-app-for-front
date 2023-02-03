package io.core.app.models.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "COMPANY_STAGING")// with progress user is keywordof
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class CompanyDto {
    @CsvBindByPosition(position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String domain;
    @CsvBindByPosition(position = 3)
    private String year;
    @CsvBindByPosition(position = 4)
    private String founded;
    @CsvBindByPosition(position = 5)
    private String industry;
    @CsvBindByPosition(position = 6)
    private String size;
    @CsvBindByPosition(position = 7)
    private String range;
    @CsvBindByPosition(position = 8)
    private String locality;
    @CsvBindByPosition(position = 9)
    private String country;
    @CsvBindByPosition(position = 10)
    private String linkedin;
    @CsvBindByPosition(position = 11)
    private String url;
    @CsvBindByPosition(position = 12)
    private String current;
    @CsvBindByPosition(position = 13)
    private String employeeEstimate;
    @CsvBindByPosition(position = 14)
    private String total;
    @CsvBindByPosition(position = 15)
    private String employee;
    @CsvBindByPosition(position = 16)
    private String estimate;

}
