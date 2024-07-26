package trento.systems.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    protected LocalDateTime createdDate;

    @CreatedBy
    @Column(name = "createdBy", updatable = false, columnDefinition = "nvarchar(4000)")
    protected String createdBy;

    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "nvarchar(4000)")
    protected String lastModifiedBy;

}