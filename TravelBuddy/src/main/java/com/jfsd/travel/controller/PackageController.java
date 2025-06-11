//package com.jfsd.travel.controller;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.jfsd.travel.model.TourPackage;
//import com.jfsd.travel.service.TourPackageService;
//
//@Controller
//public class PackageController {
//    @Autowired
//    private TourPackageService tourPackageService;
//
//    @GetMapping("/packages")
//    public String packages(
//            @RequestParam(value = "search", required = false, defaultValue = "") String search,
//            @RequestParam(value = "destination", required = false, defaultValue = "") String destination,
//            @RequestParam(value = "duration", required = false, defaultValue = "") String duration,
//            @RequestParam(value = "priceMin", required = false, defaultValue = "0") double priceMin,
//            @RequestParam(value = "priceMax", required = false, defaultValue = "10000") double priceMax,
//            Model model) {
//        List<TourPackage> packages = tourPackageService.getAllPackages();
//        List<TourPackage> filteredPackages = packages.stream()
//                .filter(pkg -> (search.isEmpty() || pkg.getTitle().toLowerCase().contains(search.toLowerCase()) || pkg.getDestination().toLowerCase().contains(search.toLowerCase())))
//                .filter(pkg -> (destination.isEmpty() || pkg.getDestination().equals(destination)))
//                .filter(pkg -> (duration.isEmpty() || pkg.getDuration().equals(duration)))
//                .filter(pkg -> (pkg.getPrice() >= priceMin && pkg.getPrice() <= priceMax))
//                .collect(Collectors.toList());
//
//        List<String> destinations = packages.stream().map(TourPackage::getDestination).distinct().collect(Collectors.toList());
//        List<String> durations = packages.stream().map(TourPackage::getDuration).distinct().collect(Collectors.toList());
//
//        model.addAttribute("packages", filteredPackages);
//        model.addAttribute("destinations", destinations);
//        model.addAttribute("durations", durations);
//        model.addAttribute("search", search);
//        model.addAttribute("selectedDestination", destination);
//        model.addAttribute("selectedDuration", duration);
//        model.addAttribute("priceMin", priceMin);
//        model.addAttribute("priceMax", priceMax);
//        return "packages";
//    }
//    
//    @GetMapping("/packages/{id}")
//    public String viewPackageDetails(@PathVariable Long id, Model model) {
//        TourPackage tourPackage = tourPackageService.getPackageById(id);
//        if (tourPackage == null) {
//            return "redirect:/packages?error=notfound";
//        }
//        model.addAttribute("tourPackage", tourPackage);
//        return "package-details"; // JSP file to render package details
//    }
//    
//}











package com.jfsd.travel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jfsd.travel.model.TourPackage;
import com.jfsd.travel.service.TourPackageService;

@Controller
public class PackageController {
    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping("/packages")
    public String packages(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "destination", required = false, defaultValue = "") String destination,
            @RequestParam(value = "duration", required = false, defaultValue = "") String duration,
            @RequestParam(value = "priceMin", required = false, defaultValue = "0") double priceMin,
            @RequestParam(value = "priceMax", required = false, defaultValue = "10000") double priceMax,
            Model model) {
        List<TourPackage> packages = tourPackageService.getAllPackages();
        List<TourPackage> filteredPackages = packages.stream()
                .filter(pkg -> (search.isEmpty() || pkg.getTitle().toLowerCase().contains(search.toLowerCase()) || pkg.getDestination().toLowerCase().contains(search.toLowerCase())))
                .filter(pkg -> (destination.isEmpty() || pkg.getDestination().equals(destination)))
                .filter(pkg -> (duration.isEmpty() || pkg.getDuration().equals(duration)))
                .filter(pkg -> (pkg.getPrice() >= priceMin && pkg.getPrice() <= priceMax))
                .collect(Collectors.toList());

        List<String> destinations = packages.stream().map(TourPackage::getDestination).distinct().collect(Collectors.toList());
        List<String> durations = packages.stream().map(TourPackage::getDuration).distinct().collect(Collectors.toList());

        model.addAttribute("packages", filteredPackages);
        model.addAttribute("destinations", destinations);
        model.addAttribute("durations", durations);
        model.addAttribute("search", search);
        model.addAttribute("selectedDestination", destination);
        model.addAttribute("selectedDuration", duration);
        model.addAttribute("priceMin", priceMin);
        model.addAttribute("priceMax", priceMax);
        return "packages";
    }
    
    @GetMapping("/packages/{id}")
    public String viewPackageDetails(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getPackageById(id);
        if (tourPackage == null) {
            // Log the error and redirect with an error message
            System.out.println("Tour package with ID " + id + " not found.");
            return "redirect:/packages?error=notfound";
        }
        model.addAttribute("tourPackage", tourPackage);
        return "package-details";
    }
}