package io.core.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "INDUSTRY")// with progress user is keywordof
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Industry {

    @Id
    @NotNull(message = "Name cannot be null")
    @Size(min = 10, max = 200, message = "Name Me must be between 10 and 200 characters")
    private String name;

}
