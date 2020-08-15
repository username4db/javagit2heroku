package org.username4db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.username4db.vo.Rec;

public interface RecordRepo extends JpaRepository<Rec, String> {

}
