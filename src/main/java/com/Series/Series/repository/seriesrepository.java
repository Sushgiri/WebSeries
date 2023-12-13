package com.Series.Series.repository;

import com.Series.Series.entity.series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface seriesrepository extends JpaRepository<series,Long> {
}
