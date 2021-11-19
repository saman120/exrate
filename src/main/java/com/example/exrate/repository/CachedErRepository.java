package com.example.exrate.repository;

import com.example.exrate.model.CachedExchangeRate;
import com.example.exrate.model.CachedExchangeRatePk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CachedErRepository extends JpaRepository<CachedExchangeRate, CachedExchangeRatePk> {
    @Query("select c from CachedExchangeRate c where c.code in ?1 and c.effectiveDate = ?2")
    List<CachedExchangeRate> getDataBy(List<String> curCodeArray, LocalDate date);

    List<CachedExchangeRate> findByEffectiveDate(LocalDate date);

    @Query("select 1 from CachedExchangeRate c where c.effectiveDate = ?1")
    List<Integer> hasDataOf(LocalDate date, Pageable pageable);

    @Query("select 1 from CachedExchangeRate c where c.effectiveDate = ?1 and ask != null")
    List<Integer> hasDataOfAsk(LocalDate date, Pageable pageable);

    @Query("select 1 from CachedExchangeRate c where c.effectiveDate = ?1 and mid != null")
    List<Integer> hasDataOfMid(LocalDate date, Pageable pageable);
}
