package daos;

import entities.Review;
import csv.services.CsvReader;

import java.util.*;
import java.util.stream.Collectors;

class ReviewId implements Comparable<ReviewId> {
    private Long userId;
    private Long productId;

    public ReviewId(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int compareTo(ReviewId other) {
        if (Objects.equals(this.productId, other.productId)) {
            return this.userId.compareTo(other.userId);
        }
        return this.productId.compareTo(other.productId);
    }
}

public class ReviewDAO {
    private Map<ReviewId, Review> reviewDB = new TreeMap<>();

    public ReviewDAO() {
        List<Review> reviews;

        try {
            reviews = CsvReader.getInstance().readAll(Review.class,
                    "src/main/resources/csv/reviews.csv");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (Review review : reviews) {
            reviewDB.put(new ReviewId(review.getIdClient(), review.getIdProdus()),
                    review);
        }
    }

    public void saveReview(Review review) {
        ReviewId reviewId = new ReviewId(review.getIdClient(), review.getIdProdus());
        reviewDB.put(reviewId, review);
    }

    public void removeReview(Long userId, Long produsId) {
        ReviewId reviewId = new ReviewId(userId, produsId);
        reviewDB.remove(reviewId);
    }

    public List<Review> getUserReviews(Long userId) {
        List<Review> reviews = new ArrayList<>();

        Set<Map.Entry<ReviewId, Review>> entries = reviewDB.entrySet();

        entries.stream().filter(entry ->
                        entry.getKey().getUserId().equals(userId))
                .toList()
                .forEach(entry -> reviews.add(entry.getValue()));

        return reviews;
    }

    public List<Review> getProductReviews(Long productId) {
        List<Review> reviews = new ArrayList<>();

        Set<Map.Entry<ReviewId, Review>> entries = reviewDB.entrySet();

        entries.stream().filter(entry ->
                entry.getKey().getProductId().equals(productId))
                .toList()
                .forEach(entry -> reviews.add(entry.getValue()));

        return reviews;
    }
}
