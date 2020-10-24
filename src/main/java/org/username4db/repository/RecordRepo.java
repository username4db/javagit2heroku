package org.username4db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.username4db.entity.Rec;

public interface RecordRepo extends JpaRepository<Rec, String> {

}
