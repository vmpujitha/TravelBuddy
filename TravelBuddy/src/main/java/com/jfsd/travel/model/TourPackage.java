//package com.jfsd.travel.model;
//
//import java.util.List;
//
//import jakarta.persistence.CollectionTable;
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "tour_packages")
//public class TourPackage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String title;
//    private String destination;
//    private String duration;
//    private double price;
//    private String imageUrl;
//    private String description;
//    private boolean featured;
//    @ElementCollection
//    private List<String> included;
//    @ElementCollection
//    private List<String> excluded;
//    @ElementCollection
//    @CollectionTable(name = "tour_package_itinerary", joinColumns = @JoinColumn(name = "tour_package_id"))
//    private List<ItineraryItem> itinerary;
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getDestination() {
//		return destination;
//	}
//	public void setDestination(String destination) {
//		this.destination = destination;
//	}
//	public String getDuration() {
//		return duration;
//	}
//	public void setDuration(String duration) {
//		this.duration = duration;
//	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	public String getImageUrl() {
//		return imageUrl;
//	}
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public boolean isFeatured() {
//		return featured;
//	}
//	public void setFeatured(boolean featured) {
//		this.featured = featured;
//	}
//	public List<String> getIncluded() {
//		return included;
//	}
//	public void setIncluded(List<String> included) {
//		this.included = included;
//	}
//	public List<String> getExcluded() {
//		return excluded;
//	}
//	public void setExcluded(List<String> excluded) {
//		this.excluded = excluded;
//	}
//	public List<ItineraryItem> getItinerary() {
//		return itinerary;
//	}
//	public void setItinerary(List<ItineraryItem> itinerary) {
//		this.itinerary = itinerary;
//	}
//}
//
//
//@Embeddable
//class ItineraryItem {
//    private int day;
//    private String title;
//    private String description;
//
//    // Getters and Setters
//    public int getDay() { return day; }
//    public void setDay(int day) { this.day = day; }
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//}






package com.jfsd.travel.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tour_packages")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Destination is required")
    private String destination;

    @NotEmpty(message = "Duration is required")
    private String duration;

    @Positive(message = "Price must be positive")
    private double price;

    @NotEmpty(message = "Image URL is required")
    private String imageUrl;

    @NotEmpty(message = "Description is required")
    private String description;

    private boolean featured; // No validation needed for boolean

    @ElementCollection
    @CollectionTable(name = "tour_package_included", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<String> included;

    @ElementCollection
    @CollectionTable(name = "tour_package_excluded", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<String> excluded;

    @ElementCollection
    @CollectionTable(name = "tour_package_itinerary", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<ItineraryItem> itinerary;

    @ElementCollection
    @CollectionTable(name = "tour_package_photos", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<String> photos;

    @ElementCollection
    @CollectionTable(name = "tour_package_amenities", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<String> amenities;

    @ElementCollection
    @CollectionTable(name = "tour_package_rooms", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<RoomOption> roomOptions;

    @ElementCollection
    @CollectionTable(name = "tour_package_offers", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<String> offers;

    @ElementCollection
    @CollectionTable(name = "tour_package_key_offerings", joinColumns = @JoinColumn(name = "tour_package_id"))
    private List<String> keyOfferings;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
    public List<String> getIncluded() { return included; }
    public void setIncluded(List<String> included) { this.included = included; }
    public List<String> getExcluded() { return excluded; }
    public void setExcluded(List<String> excluded) { this.excluded = excluded; }
    public List<ItineraryItem> getItinerary() { return itinerary; }
    public void setItinerary(List<ItineraryItem> itinerary) { this.itinerary = itinerary; }
    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
    public List<RoomOption> getRoomOptions() { return roomOptions; }
    public void setRoomOptions(List<RoomOption> roomOptions) { this.roomOptions = roomOptions; }
    public List<String> getOffers() { return offers; }
    public void setOffers(List<String> offers) { this.offers = offers; }
    public List<String> getKeyOfferings() { return keyOfferings; }
    public void setKeyOfferings(List<String> keyOfferings) { this.keyOfferings = keyOfferings; }

    // toString
    @Override
    public String toString() {
        return "TourPackage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", destination='" + destination + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", featured=" + featured +
                ", included=" + included +
                ", excluded=" + excluded +
                ", itinerary=" + itinerary +
                ", photos=" + photos +
                ", amenities=" + amenities +
                ", roomOptions=" + roomOptions +
                ", offers=" + offers +
                ", keyOfferings=" + keyOfferings +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourPackage that = (TourPackage) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                featured == that.featured &&
                Objects.equals(title, that.title) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(description, that.description) &&
                Objects.equals(included, that.included) &&
                Objects.equals(excluded, that.excluded) &&
                Objects.equals(itinerary, that.itinerary) &&
                Objects.equals(photos, that.photos) &&
                Objects.equals(amenities, that.amenities) &&
                Objects.equals(roomOptions, that.roomOptions) &&
                Objects.equals(offers, that.offers) &&
                Objects.equals(keyOfferings, that.keyOfferings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, destination, duration, price, imageUrl, description, featured,
                included, excluded, itinerary, photos, amenities, roomOptions, offers, keyOfferings);
    }
}



//
//@Embeddable
//class RoomOption {
//    private String roomType; // e.g., "Standard Room", "Deluxe Room"
//    private double price; // Price per night
//    private double taxesAndFees; // Taxes and fees per night
//    private int squareFeet; // Room size in square feet
//    private String features; // e.g., "Queen Bed,Air Conditioning"
//    private String offers; // e.g., "10% off on Spa,Breakfast Included"
//    private boolean refundable; // Is the booking refundable?
//
//    // Getters and Setters
//    public String getRoomType() { return roomType; }
//    public void setRoomType(String roomType) { this.roomType = roomType; }
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
//    public double getTaxesAndFees() { return taxesAndFees; }
//    public void setTaxesAndFees(double taxesAndFees) { this.taxesAndFees = taxesAndFees; }
//    public int getSquareFeet() { return squareFeet; }
//    public void setSquareFeet(int squareFeet) { this.squareFeet = squareFeet; }
//    public String getFeatures() { return features; }
//    public void setFeatures(String features) { this.features = features; }
//    public String getOffers() { return offers; }
//    public void setOffers(String offers) { this.offers = offers; }
//    public boolean isRefundable() { return refundable; }
//    public void setRefundable(boolean refundable) { this.refundable = refundable; }
//}

