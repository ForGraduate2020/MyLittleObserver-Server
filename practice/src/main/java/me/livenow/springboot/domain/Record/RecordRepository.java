package me.livenow.springboot.domain.Record;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<MloRecord, Long> {
}
