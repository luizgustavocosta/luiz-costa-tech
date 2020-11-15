package tech.costa.luiz.reactive_db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("examples")
class Example {
    
    @Id
    @Column("id")
    private Integer id;

    @Column("title")
    private String title;

    @Column("content")
    private String content;
    
}
