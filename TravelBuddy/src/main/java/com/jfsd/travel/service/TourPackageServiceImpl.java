package com.jfsd.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfsd.travel.model.TourPackage;
import com.jfsd.travel.repository.TourPackageRepository;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TourPackage> getAllPackages() {
        List<TourPackage> packages = tourPackageRepository.findAll();
        // Initialize collections to avoid LazyInitializationException
        packages.forEach(pkg -> {
            pkg.getItinerary().size(); // Force initialization
            pkg.getRoomOptions().size();
            pkg.getPhotos().size();
            pkg.getAmenities().size();
            pkg.getOffers().size();
            pkg.getKeyOfferings().size();
            pkg.getIncluded().size();
            pkg.getExcluded().size();
        });
        return packages;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourPackage> getFeaturedPackages() {
        List<TourPackage> featuredPackages = tourPackageRepository.findByFeaturedTrue();
        // Initialize collections
        featuredPackages.forEach(pkg -> {
            pkg.getItinerary().size();
            pkg.getRoomOptions().size();
            pkg.getPhotos().size();
            pkg.getAmenities().size();
            pkg.getOffers().size();
            pkg.getKeyOfferings().size();
            pkg.getIncluded().size();
            pkg.getExcluded().size();
        });
        return featuredPackages;
    }

    @Override
    @Transactional(readOnly = true)
    public TourPackage getPackageById(Long id) {
        return tourPackageRepository.findById(id).map(pkg -> {
            // Initialize collections
            pkg.getItinerary().size();
            pkg.getRoomOptions().size();
            pkg.getPhotos().size();
            pkg.getAmenities().size();
            pkg.getOffers().size();
            pkg.getKeyOfferings().size();
            pkg.getIncluded().size();
            pkg.getExcluded().size();
            return pkg;
        }).orElse(null);
    }

    @Override
    @Transactional
    public void save(TourPackage tourPackage) {
        tourPackageRepository.save(tourPackage);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tourPackageRepository.deleteById(id);
    }
}