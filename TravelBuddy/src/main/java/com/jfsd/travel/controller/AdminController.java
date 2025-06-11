package com.jfsd.travel.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jfsd.travel.model.Booking;
import com.jfsd.travel.model.BookingStatus;
import com.jfsd.travel.model.Destination;
import com.jfsd.travel.model.ItineraryItem;
import com.jfsd.travel.model.RoomOption;
import com.jfsd.travel.model.Testimonial;
import com.jfsd.travel.model.TourPackage;
import com.jfsd.travel.model.User;
import com.jfsd.travel.service.BookingService;
import com.jfsd.travel.service.DestinationService;
import com.jfsd.travel.service.TestimonialService;
import com.jfsd.travel.service.TourPackageService;
import com.jfsd.travel.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private TourPackageService tourPackageService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private TestimonialService testimonialService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminDashboard(Model model) {
        long destinationCount = destinationService.getAllDestinations().size();
        long packageCount = tourPackageService.getAllPackages().size();
        long bookingCount = bookingService.findAll().size();
        long testimonialCount = testimonialService.getAllTestimonials().size();
        long userCount = userService.findAll().size();

        model.addAttribute("destinationCount", destinationCount);
        model.addAttribute("packageCount", packageCount);
        model.addAttribute("bookingCount", bookingCount);
        model.addAttribute("testimonialCount", testimonialCount);
        model.addAttribute("userCount", userCount);
        return "admin/dashboard";
    }

    // Destinations Management
    @GetMapping("destinations")
    public String manageDestinations(Model model) {
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "admin/destinations";
    }

    @GetMapping("destinations/add")
    public String addDestinationForm(Model model) {
        model.addAttribute("destination", new Destination());
        return "admin/add-destination";
    }

    @PostMapping("destinations/add")
    public String addDestination(@ModelAttribute Destination destination) {
        destinationService.save(destination);
        return "redirect:/admin/destinations";
    }

    @GetMapping("destinations/edit/{id}")
    public String editDestinationForm(@PathVariable Long id, Model model) {
        model.addAttribute("destination", destinationService.getDestinationById(id));
        return "admin/edit-destination";
    }

    @PostMapping("destinations/edit/{id}")
    public String editDestination(@PathVariable Long id, @ModelAttribute Destination destination) {
        destination.setId(id);
        destinationService.save(destination);
        return "redirect:/admin/destinations";
    }

    @GetMapping("/destinations/delete/{id}")
    public String deleteDestination(@PathVariable Long id) {
        destinationService.delete(id);
        return "redirect:/admin/destinations";
    }

    // Tour Packages Management
    @GetMapping("/packages")
    public String listPackages(Model model) {
        List<TourPackage> packages = tourPackageService.getAllPackages();
        model.addAttribute("packages", packages);
        return "admin/packages";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Editor for photos (List<String>)
        binder.registerCustomEditor(List.class, "photos", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing photos: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> photos = Arrays.stream(text.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    setValue(photos);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse photos: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for amenities (List<String>)
        binder.registerCustomEditor(List.class, "amenities", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing amenities: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> amenities = Arrays.stream(text.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    setValue(amenities);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse amenities: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for offers (List<String>)
        binder.registerCustomEditor(List.class, "offers", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing offers: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> offers = Arrays.stream(text.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    setValue(offers);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse offers: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for keyOfferings (List<String>)
        binder.registerCustomEditor(List.class, "keyOfferings", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing keyOfferings: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> keyOfferings = Arrays.stream(text.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    setValue(keyOfferings);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse keyOfferings: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for included (List<String>) - Added
        binder.registerCustomEditor(List.class, "included", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing included: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> included = Arrays.stream(text.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    setValue(included);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse included: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for excluded (List<String>) - Added
        binder.registerCustomEditor(List.class, "excluded", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing excluded: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> excluded = Arrays.stream(text.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    setValue(excluded);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse excluded: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for roomOptions (List<RoomOption>)
     // Editor for roomOptions (List<RoomOption>)
        binder.registerCustomEditor(List.class, "roomOptions", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing roomOptions: " + text);
                List<RoomOption> roomOptions = new ArrayList<>();
                if (text == null || text.trim().isEmpty()) {
                    setValue(roomOptions);
                    return;
                }
                try {
                    List<String> roomOptionStrings = Arrays.stream(text.split(";"))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    for (String roomOptionString : roomOptionStrings) {
                        try {
                            RoomOption roomOption = parseRoomOption(roomOptionString);
                            if (roomOption != null) {
                                roomOptions.add(roomOption);
                            } else {
                                System.out.println("Skipping invalid room option: " + roomOptionString);
                            }
                        } catch (Exception e) {
                            System.out.println("Skipping invalid room option: " + roomOptionString + ", Error: " + e.getMessage());
                        }
                    }
                    setValue(roomOptions);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse room options: " + text + ", Error: " + e.getMessage());
                }
            }
        });

        // Editor for itinerary (List<ItineraryItem>)
        binder.registerCustomEditor(List.class, "itinerary", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                System.out.println("Parsing itinerary: " + text);
                if (text == null || text.trim().isEmpty()) {
                    setValue(new ArrayList<>());
                    return;
                }
                try {
                    List<String> itineraryItemStrings = Arrays.stream(text.split(";"))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                    List<ItineraryItem> itineraryItems = new ArrayList<>();
                    for (String itineraryItemString : itineraryItemStrings) {
                        ItineraryItem itineraryItem = parseItineraryItem(itineraryItemString);
                        if (itineraryItem != null) {
                            itineraryItems.add(itineraryItem);
                        }
                    }
                    setValue(itineraryItems);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to parse itinerary items: " + text + ", Error: " + e.getMessage());
                }
            }
        });
    }

    // Helper method to parse a single RoomOption from a string
    private RoomOption parseRoomOption(String roomOptionString) {
        System.out.println("Parsing RoomOption: " + roomOptionString);
        String[] parts = roomOptionString.split("\\|");
        // Log the parts for debugging
        System.out.println("Parts: " + Arrays.toString(parts));

        // Instead of throwing an exception, provide defaults for missing parts
        if (parts.length != 7) {
            System.out.println("Warning: Expected 7 parts (roomType|price|taxesAndFees|squareFeet|features|offers|refundable), but got " + parts.length + " parts in: " + roomOptionString);
            // Pad the array with empty strings to ensure 7 parts
            parts = Arrays.copyOf(parts, 7);
            for (int i = 0; i < 7; i++) {
                if (parts[i] == null) {
                    parts[i] = "";
                }
            }
        }

        try {
            RoomOption roomOption = new RoomOption();
            // roomType (String, default to empty string if missing)
            roomOption.setRoomType(parts[0].trim().isEmpty() ? "Unknown Room Type" : parts[0].trim());

            // price (Double, default to 0.0 if invalid)
            try {
                roomOption.setPrice(parts[1].trim().isEmpty() ? 0.0 : Double.parseDouble(parts[1].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + parts[1] + ", defaulting to 0.0");
                roomOption.setPrice(0.0);
            }

            // taxesAndFees (Double, default to 0.0 if invalid)
            try {
                roomOption.setTaxesAndFees(parts[2].trim().isEmpty() ? 0.0 : Double.parseDouble(parts[2].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid taxesAndFees format: " + parts[2] + ", defaulting to 0.0");
                roomOption.setTaxesAndFees(0.0);
            }

            // squareFeet (Integer, default to 0 if invalid)
            try {
                roomOption.setSquareFeet(parts[3].trim().isEmpty() ? 0 : Integer.parseInt(parts[3].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid squareFeet format: " + parts[3] + ", defaulting to 0");
                roomOption.setSquareFeet(0);
            }

            // features (String, default to empty string if empty)
            roomOption.setFeatures(parts[4].trim().isEmpty() ? "" : parts[4].trim());

            // offers (String, default to empty string if empty)
            roomOption.setOffers(parts[5].trim().isEmpty() ? "" : parts[5].trim());

            // refundable (Boolean, default to false if invalid)
            try {
                roomOption.setRefundable(parts[6].trim().isEmpty() ? false : Boolean.parseBoolean(parts[6].trim()));
            } catch (Exception e) {
                System.out.println("Invalid refundable format: " + parts[6] + ", defaulting to false");
                roomOption.setRefundable(false);
            }

            return roomOption;
        } catch (Exception e) {
            System.out.println("Failed to parse RoomOption: " + roomOptionString + ", Error: " + e.getMessage());
            return null; // Return null to skip this entry
        }
    }
    
    private ItineraryItem parseItineraryItem(String itineraryItemString) {
        String[] parts = itineraryItemString.split("\\|");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Expected 3 parts (day|title|description), but got " + parts.length + " parts in: " + itineraryItemString);
        }
        try {
            ItineraryItem itineraryItem = new ItineraryItem();
            itineraryItem.setDay(Integer.parseInt(parts[0].trim()));
            itineraryItem.setTitle(parts[1].trim());
            itineraryItem.setDescription(parts[2].trim());
            return itineraryItem;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid itinerary item format: " + itineraryItemString + ", Error: " + e.getMessage());
        }
    }

    @GetMapping("/packages/add")
    public String addPackageForm(Model model) {
        model.addAttribute("tourPackage", new TourPackage());
        return "admin/add-package";
    }

    @PostMapping("/packages/add")
    public String addPackage(@Valid @ModelAttribute("tourPackage") TourPackage tourPackage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return "admin/add-package";
        }
        try {
            tourPackageService.save(tourPackage);
            return "redirect:/admin/packages";
        } catch (Exception e) {
            System.out.println("Error saving package: " + e.getMessage());
            model.addAttribute("error", "Failed to add package: " + e.getMessage());
            return "admin/add-package";
        }
    }

    @GetMapping("/packages/edit/{id}")
    public String editPackageForm(@PathVariable Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getPackageById(id);
        if (tourPackage == null) {
            return "redirect:/admin/packages?error=notfound";
        }
        model.addAttribute("tourPackage", tourPackage);
        return "admin/edit-package";
    }

    @PostMapping("/packages/edit/{id}")
    public String editPackage(@PathVariable Long id, @Valid @ModelAttribute("tourPackage") TourPackage tourPackage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return "admin/edit-package";
        }
        try {
            tourPackage.setId(id);
            tourPackageService.save(tourPackage);
            return "redirect:/admin/packages";
        } catch (Exception e) {
            System.out.println("Error updating package: " + e.getMessage());
            model.addAttribute("error", "Failed to update package: " + e.getMessage());
            return "admin/edit-package";
        }
    }

    @GetMapping("/packages/delete/{id}")
    public String deletePackage(@PathVariable Long id) {
        tourPackageService.delete(id);
        return "redirect:/admin/packages";
    }

//    private RoomOption parseRoomOption(String roomOptionStr) {
//        try {
//            String[] parts = roomOptionStr.split("\\|");
//            if (parts.length != 7) {
//                throw new IllegalArgumentException("Expected 7 parts (roomType|price|taxesAndFees|squareFeet|features|offers|refundable), but got " + parts.length + " parts in: " + roomOptionStr);
//            }
//            RoomOption roomOption = new RoomOption();
//            roomOption.setRoomType(parts[0].trim());
//            roomOption.setPrice(Double.parseDouble(parts[1].trim()));
//            roomOption.setTaxesAndFees(Double.parseDouble(parts[2].trim()));
//            roomOption.setSquareFeet(Integer.parseInt(parts[3].trim()));
//            roomOption.setFeatures(parts[4].trim());
//            roomOption.setOffers(parts[5].trim());
//            roomOption.setRefundable(Boolean.parseBoolean(parts[6].trim()));
//            return roomOption;
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Invalid number format in room option: " + roomOptionStr + ", Error: " + e.getMessage());
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid room option format: " + roomOptionStr + ", Error: " + e.getMessage());
//        }
//    }
//
//    private ItineraryItem parseItineraryItem(String itineraryItemStr) {
//        try {
//            String[] parts = itineraryItemStr.split("\\|");
//            if (parts.length != 3) {
//                throw new IllegalArgumentException("Expected 3 parts (day|title|description), but got " + parts.length + " parts in: " + itineraryItemStr);
//            }
//            ItineraryItem itineraryItem = new ItineraryItem();
//            itineraryItem.setDay(Integer.parseInt(parts[0].trim()));
//            itineraryItem.setTitle(parts[1].trim());
//            itineraryItem.setDescription(parts[2].trim());
//            return itineraryItem;
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Invalid number format in itinerary item: " + itineraryItemStr + ", Error: " + e.getMessage());
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid itinerary item format: " + itineraryItemStr + ", Error: " + e.getMessage());
//        }
//    }

    // Bookings Management
    @GetMapping("/bookings")
    public String manageBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAll());
        return "admin/bookings";
    }

    @GetMapping("/bookings/approve/{id}")
    public String approveBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(BookingStatus.APPROVED);
            bookingService.save(booking);
        }
        return "redirect:/admin/bookings";
    }

    @GetMapping("/bookings/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingService.save(booking);
        }
        return "redirect:/admin/bookings";
    }

    // Testimonials Management
    @GetMapping("/testimonials")
    public String manageTestimonials(Model model) {
        model.addAttribute("testimonials", testimonialService.getAllTestimonials());
        return "admin/testimonials";
    }

    @GetMapping("/testimonials/add")
    public String addTestimonialForm(Model model) {
        model.addAttribute("testimonial", new Testimonial());
        return "admin/add-testimonial";
    }

    @PostMapping("/testimonials/add")
    public String addTestimonial(@ModelAttribute Testimonial testimonial) {
        testimonialService.save(testimonial);
        return "redirect:/admin/testimonials";
    }

    @GetMapping("/testimonials/edit/{id}")
    public String editTestimonialForm(@PathVariable Long id, Model model) {
        model.addAttribute("testimonial", testimonialService.findById(id).orElse(null));
        return "admin/edit-testimonial";
    }

    @PostMapping("/testimonials/edit/{id}")
    public String editTestimonial(@PathVariable Long id, @ModelAttribute Testimonial testimonial) {
        testimonial.setId(id);
        testimonialService.save(testimonial);
        return "redirect:/admin/testimonials";
    }

    @GetMapping("/testimonials/delete/{id}")
    public String deleteTestimonial(@PathVariable Long id) {
        testimonialService.delete(id);
        return "redirect:/admin/testimonials";
    }

    // Users Management
    @GetMapping("users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/users/enable/{id}")
    public String enableUser(@PathVariable Long id) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            user.setEnabled(true);
            userService.save(user);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/users/disable/{id}")
    public String disableUser(@PathVariable Long id) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            user.setEnabled(false);
            userService.save(user);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("login")
    public String adminLoginForm() {
        return "admin/login";
    }

    @PostMapping("login")
    public String adminLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // Hardcoded admin check (replace with proper authentication in production)
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("admin", true);
            return "redirect:/admin";
        }
        return "admin/login?error=true";
    }

    @GetMapping("logout")
    public String adminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}