package com.jfsd.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jfsd.travel.model.TourPackage;

public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
    List<TourPackage> findByFeaturedTrue();
}